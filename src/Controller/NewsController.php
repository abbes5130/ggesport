<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManagerInterface;
use App\Form\NewsType;
use App\Entity\Comments;
use App\Entity\News;
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
public function deletenewsAction(Request $request)
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

    $comment->setNews($post);
    $comment->setText($request->request->get('comment'));
    $comment->setCommentDate(new \DateTime('now'));   
    $em = $this->getDoctrine()->getManager();
    $em->persist($comment);
    $em->flush();

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

public function showdetailedAction($id)
{
    $em= $this->getDoctrine()->getManager();
    $p=$em->getRepository(News::class)->find($id);
    return $this->render('news/details/detail.html.twig', array(
        'title'=>$p->getTitle(),
        'bg_img'=>$p->getBgImg(),
        'img'=>$p->getImg(),
        'descripion'=>$p->getDescription(),
        'creation_date'=>$p->getCreationDate(),
        'News'=>$p,
        'Comments'=>$p,
        'id'=>$p->getId()
    ));
}
}
