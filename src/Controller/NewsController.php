<?php

namespace App\Controller;
use Symfony\Component\DependencyInjection\ContainerBuilder;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManagerInterface;
use App\Form\NewsType;
use App\Entity\Comments;
use App\Entity\News;
use App\Entity\Category;

use App\Entity\Likes;
use Prophecy\Argument\Token\TokenInterface;
use Psr\Log\LoggerInterface;
use Symfony\Component\DependencyInjection\TaggedContainerInterface;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;
use Knp\Component\Pager\PaginatorInterface ;

class NewsController extends AbstractController
{
    /**
     * @Route("/news", name="app_news")
     */
    public function index(): Response
    {
        
        return $this->render('news/actualite/index.html.twig', [
            'controller_name' => 'NewsController',
        ]);
    }
    public function addAction(Request $request)
    {

        $post = new News();
        $form= $this->createForm(NewsType::class, $post);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $file = $form->get('img')->getData();
            $file1 = $form->get('bg_img')->getData();
           // $file = $post->getImg();
            //$file1 = $post->getBgImg();
            $filename1= md5(uniqid()) . '.' . $file1->guessExtension();

            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('news_directory'), $filename);
            $file1->move($this->getParameter('news_directory'), $filename1);

            $post->setImg($filename);
            $post->setBgImg($filename1);
            
           // $post->setDescription($this->getUser());
            $post->setCreationDate(new \DateTime('now'));

            $em->persist($post);
            $em->flush();

            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('home');

        }
        return $this->render('news/actualite/index.html.twig', array(
            "Form"=> $form->createView()
        ));
    }
    /**
     * @Route("/update/{id}" , name="updateactu")
     */
    public function updatenewsAction(Request $request, $id)
{
    $em=$this->getDoctrine()->getManager();
    $p= $em->getRepository(News::class)->find($id);
    $form=$this->createForm(NewsType::class,$p);
    $form->handleRequest($request);
    if($form->isSubmitted()){
        //$file = $p->getImg();
                //$file1 = $p->getBgImg();

        $file = $form->get('img')->getData();
        $file1 = $form->get('bg_img')->getData();
        $filename1= md5(uniqid()) . '.' . $file1->guessExtension();
        $file1->move($this->getParameter('news_directory'), $filename1);

        $filename= md5(uniqid()) . '.' . $file->guessExtension();
        $file->move($this->getParameter('news_directory'), $filename);
        $p->setImg($filename);
        $p->setBgImg($filename1);

        $p->setCreationDate(new \DateTime('now'));
        $em= $this->getDoctrine()->getManager();
        $em->persist($p);
        $em->flush();
        return $this->redirectToRoute('home');

    }

return $this->render('news/actualite/update.html.twig', array(
    "form"=> $form->createView()
));
}
public function deletenewsAction(Request $request ,TaggedContainerInterface $s)
{
    $id = $request->get('id');
    $em= $this->getDoctrine()->getManager();
    $news=$em->getRepository(News::class)->find($id);
    $em->remove($news);
    $em->flush();
    return $this->redirectToRoute('home');
}
public function addCommentAction(Request $request)
{
    
    $ref = $request->headers->get('referer');

    $post = $this->getDoctrine()
        ->getRepository(News::class)
        ->findPostByid($request->request->get('news_id'));

    $comment = new Comments();
    //$container=$request->request->get('comment');

    $comment->setNews($post);
    $comment->setText($request->request->get('comment'));
    $comment->setCommentDate(new \DateTime('now'));   
    $em = $this->getDoctrine()->getManager();
    $em->persist($comment);
    $em->flush();
    //$message = new \MartinGeorgiev\SocialPost\Message('your test message');
       //dd($message); 
       
       //dd($container); 
     // $container->get('social_post')->publish($message);
  // $user-> $em->getParameter('social_post')->publish($message);

    $this->addFlash(
        'info', 'Comment published !.'
    );


    return $this->redirect($ref);
}
     
public function deleteCommentAction(Request $request)
{
$id = $request->get('id');
$em= $this->getDoctrine()->getManager();
$comment=$em->getRepository('App:NewsRepository')->find($id)
;
$em->remove($comment);                                              
$em->flush();
return $this->redirectToRoute('News/{id}');
}

public function showdetailedAction1($id,$id1)
{
    $em= $this->getDoctrine()->getManager();
    $p=$em->getRepository(News::class)->find($id);
    $c=$em->getRepository(Category::class)->find($id);

    $a=$em->getRepository(Comments::class)->find($id1);

    return $this->render('news/details/detail.html.twig', array(  
        'title'=>$p->getTitle(),
        'bg_img'=>$p->getBgImg(),
        'img'=>$p->getImg(),
        'descripion'=>$p->getDescription(),
        'creation_date'=>$p->getCreationDate(),
        'News'=>$p,   
        'id'=>$a->getId(),
        'comments'=>$a,
        'Likes'=>$a,
        'category'=>$c,

    ));
}
public function showdetailedAction($id)
{
    
    $em= $this->getDoctrine()->getManager();
    $p=$em->getRepository(News::class)->find($id);
    $category = $this->getDoctrine()->getRepository(Category::class)->findAll();

    return $this->render('news/details/detail.html.twig', array(
        'title'=>$p->getTitle(),
        'bg_img'=>$p->getBgImg(),
        'img'=>$p->getImg(),
        'descripion'=>$p->getDescription(),
        'creation_date'=>$p->getCreationDate(),
        'News'=>$p,
        'Comments'=>$p,
        'tag'=>$p,
        'id'=>$p->getId(),
        'id1'=>$p->getComments(),
        "categorys" => $category


        
    ));
}
public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $posts =  $em->getRepository('App:News')->findEntitiesByString($requestString);
        if(!$posts) {
            $result['News']['error'] = "News Not found :( ";
        } else {
            $result['News'] = $this->getRealEntities($posts);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($posts){
        // 
       // 
        //
        //
        
        // //$file = $p->getImg();
        //$file1 = $p->getBgImg();
        //
        foreach ($posts as $posts){
            //$file = $posts->get('img')->getData();
           // $file1 = $posts->get('bg_img')->getData()
           
            //$file1->move($this->getParameter('news_directory'), $filename1);
            //$file->move($this->getParameter('news_directory'), $filename);
            $realEntities[$posts->getId()] = [$posts->getImg(),$posts->getTitle(), $posts->getBgImg(),$posts->getDescription()];

        }
        return $realEntities;
    }
  
      /**
     * @Route("/addlike")
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     */
    public function likeAction(Request $request)
    {
        $ref = $request->headers->get('referer');

        $comment = $this->getDoctrine()
        ->getRepository(Comments::class)
        ->findPostByid($request->request->get('comments_id'));
       // $comment = $this->getDoctrine()->getRepository(Comments::class)->find(Request::createFromGlobals()->request->get('id'));

        
       //if (!$this->getDoctrine()->getRepository(Likes::class)->findOneBy(['Comments' => $comment->getId()])) {
            $like = new Likes();
            $like->setComments($comment);

            $em = $this->getDoctrine()->getManager();
            $em->persist($like);
            $em->flush();

            return $this->redirect($ref);

       // }
    }
     
    

   
}
