<?php

namespace App\Controller;
use App\Entity\News;
use App\Entity\Likes;

use App\Form\NewsType;

use App\Entity\Comments;
use App\Form\CommentsType;
use App\Repository\CommentsRepository;
use App\Repository\NewsRepository;
use Doctrine\ORM\Mapping\Id;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface ;
/**
 * @Route("/comments")
 */
class CommentsController extends AbstractController
{
    /**
     * @Route("/comments", name="index2", methods={"GET"})
     */
    public function index(CommentsRepository $commentsRepository,Request $request, PaginatorInterface $paginator,NewsRepository $NewsRepository ): Response
    {
        $search = $request->query->get('search');
        $queryBuilder = $commentsRepository->getWithSearchQueryBuilder($search);
        $pagination = $paginator->paginate(
            $queryBuilder, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            10 /*limit per page*/
        );
        return $this->render('comments/index.html.twig', [
            'comments' => $commentsRepository->findAll(),
            'news' => $NewsRepository->findAll(),
            'pagination' => $pagination,

        ]);
    }

    /**
     * @Route("/addComments2", methods={"GET", "POST"})
     */
    public function addComment(Request $request): Response
    {$News= new News();
        $comment = new Comments();
        $form = $this->createForm(CommentsType::class, $comment);
       // $form1 = $this->createForm(NewsType::class, $News);

        $form->handleRequest($request);
       // $post = $form1->get('id')->getData();
    //$post = $News->getId();

        $post = $this->getDoctrine()
        ->getRepository(News::class)
        ->findPostByid($request->request->get('news_id'));
    

        if($form->isSubmitted() && $form->isValid())
        {

            $entityManager = $this->getDoctrine()->getManager();

            $comment->setCommentDate(new \DateTime('now'));
            $entityManager->persist($comment);
            $entityManager->flush();        }

        return $this->render("comments/_form.html.twig", [
            "form_title" => "Ajouter un comment",

            "Form" => $form->createView(),
        ]);
    }
    public function addCommentsAction( Request $request)
    {
        
    $News=new News();
            $ref = $request->headers->get('referer');
           
            $post = $this->getDoctrine()
            ->getRepository(News::class)
            ->findPostByid($request->request->get('news_id'));
    
            $comment = new Comments();
            $comment->setText($request->request->get('comment'));

            $comment->setCommentDate(new \DateTime('now'));
            
            $comment->setNews($post);

    
       
            $em = $this->getDoctrine()->getManager();
            $em->persist($comment);
            $em->flush();
    
            $this->addFlash(
                'info', 'News added !.' );
               
                return $this->redirect($ref);
    
    }
    /**
 * @Route("/liComment", name="liComment")
 */
public function ListComment() 
{
    $News = $this->getDoctrine()->getRepository(Comments::class)->findAll();
    $post = $this->getDoctrine()->getRepository(News::class)->findAll();

 return $this->render('comments/index.html.twig', [
        "comment" => $News,
        "news" => $post,

    ]);
}
    /**
     * @Route("Comments/{id}",name="Comments",methods={"GET"})
     */
    public function Comment(int $id): Response
    { 
        $comment = $this->getDoctrine()->getRepository(Comments::class)->find($id);
    
        return $this->render("comments/show.html.twig", [
            "Comments" => $comment,

        ]);
    }

    /**
     * @Route("update1/{id}", name="modify_Comments", methods={"GET", "POST"})
     */
    public function modifycomment(Request $request, int $id): Response
    {
        
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository(Comments::class)->find($id);
        $form=$this->createForm(CommentsType::class,$p);
        $form->handleRequest($request);
       
        if($form->isSubmitted() && $form->isValid())
        {
          $p->setCommentDate(new \DateTime('now'));
            $em= $this->getDoctrine()->getManager();
            $em->persist($p);
            $em->flush();
        }
    
        return $this->render("comments/edit.html.twig", [
            "form_title" => "Modifier un comment",
            "Form" => $form->createView(),
        ]);
    }

    /**
     * @Route("deletecomments/{id}", methods={"POST"})
     */
    public function deletecomments(int $id): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $comment = $entityManager->getRepository(Comments::class)->find($id);
        $entityManager->remove($comment);
        $entityManager->flush();
    
        return $this->redirectToRoute("index2");
    }
    /**
 * @Route("/liNews", name="liNews")
 */
public function ListNews()
{

    return $this->render('news1/index.html.twig', [
    ]);
}
   /**
     * @Route("/Comments/like")
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     */
    public function likeAction()
    {

        $comment = $this->getDoctrine()->getRepository(Comments::class)->find(Request::createFromGlobals()->request->get('id'));

        
        if (!$this->getDoctrine()->getRepository(Likes::class)->findOneBy(['Comments' => $comment->getId()])) {
            $like = new Likes();
            $like->setComments($comment);

            $em = $this->getDoctrine()->getManager();
            $em->persist($like);
            $em->flush();

            return $this->json([
                'success' => true,
                'likesCount' => $comment->getLikes()->count()
            ]);
        }
    }

    /**
     * @Route("/Comments/unlike")
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     */
    public function unlikeAction()
    {

        $id = Request::createFromGlobals()->request->get('id');

        if ($like = $this->getDoctrine()->getRepository(Likes::class)->findOneBy(['Comments' => $id])) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($like);
            $em->flush();

            return $this->json([
                'success' => true,
                'likesCount' => $this->getDoctrine()->getRepository(Comments::class)->find($id)->getLikes()->count()
            ]);
        }
    }
}
