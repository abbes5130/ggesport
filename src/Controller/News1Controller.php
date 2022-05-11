<?php

namespace App\Controller;
use App\Entity\Newcategorie;

use App\Entity\News;
use App\Form\NewsType;
use App\Repository\NewsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\NewcategorieRepository;

/**
 * @Route("/news1")
 */
class News1Controller extends AbstractController
{
    /**
     * @Route("/", name="index1_news_news", methods={"GET"})
     */
    public function index(NewsRepository $newsRepository,NewcategorieRepository $newcateRepository): Response
    {
        return $this->render('news1/index.html.twig', [
            'news' => $newsRepository->findAll(),
            'Newcategorie' => $newcateRepository->findAll(),

        ]);
    }

    /**
     * @Route("/addNews", name="add_News")
     */
    public function addNews(Request $request): Response
    {
        $News = new News();
        $form = $this->createForm(NewsType::class, $News);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();
            //$file = $News->getImg();
            //$file1 = $News->getBgImg();
            $file = $form->get('img')->getData();
            $file1 = $form->get('bg_img')->getData();

            $filename1= md5(uniqid()) . '.' . $file1->guessExtension();

            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('news_directory'), $filename);
            $file1->move($this->getParameter('news_directory'), $filename1);

            $News->setImg($filename);
            $News->setBgImg($filename1);
            
           // $post->setDescription($this->getUser());
            $News->setCreationDate(new \DateTime('now'));
            $entityManager->persist($News);
            $entityManager->flush();        }

        return $this->render("news1/_form.html.twig", [
            "form_title" => "Ajouter un News",

            "Form" => $form->createView(),
        ]);
    }
     /**
     * @Route("/addNews1", name="add_News1")
     */
    public function addNewsAction( Request $request)
{
    

        $ref = $request->headers->get('referer');
       
        $post = $this->getDoctrine()
            ->getRepository(Newcategorie::class)
            ->findPostByid($request->request->get('news_id'));

        $News = new News();
        $News->setTitle($request->request->get('news1'));
        $News->setNewcategorie($post);

        $News->setDescription($request->request->get('news2'));
        $News->setCreationDate(new \DateTime('now'));
        
       

        $News->setImg($request->request->get('file1'));
        $News->setBgImg($request->request->get('file'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($News);
        $em->flush();

        $this->addFlash(
            'info', 'News added !.' );

            return $this->redirect($ref);

}
/**
 * @Route("/liNews", name="liNews")
 */
public function ListNews()
{
    $News = $this->getDoctrine()->getRepository(News::class)->findAll();
    $post = $this->getDoctrine()->getRepository(Newcategorie::class)->findAll();

    return $this->render('news1/index.html.twig', [
        "news" => $News,
        "Newcategorie"=>$post
    ]);
}
  /**
 * @Route("/New1/{id}", name="New1")
 */
public function New(int $id): Response
{
    $News = $this->getDoctrine()->getRepository(News::class)->find($id);

    return $this->render("news1/show.html.twig", [
        "news" => $News,
    ]);
}

   /**
 * @Route("/modify-News/{id}", name="modify_News")
 */
public function modifyNews(Request $request, int $id): Response
{
    $em=$this->getDoctrine()->getManager();
    $p= $em->getRepository(News::class)->find($id);
    $form=$this->createForm(NewsType::class,$p);
    $form->handleRequest($request);
   
    if($form->isSubmitted() && $form->isValid())
    {
        $file = $form->get('img')->getData();
        $file1 = $form->get('bg_img')->getData();
       // $file = $p->getImg();
       // $file1 = $p->getBgImg();
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
    }

    return $this->render("news1/edit.html.twig", [
        "form_title" => "Modifier un News",
        "Form" => $form->createView(),
    ]);
}

  /** 
 * @Route("/delete-News/{id}", name="delete_News")
 */
public function deleteNews(int $id): Response
{
    $entityManager = $this->getDoctrine()->getManager();
    $News = $entityManager->getRepository(News::class)->find($id);
    $entityManager->remove($News);
    $entityManager->flush();

    return $this->redirectToRoute("index1_news");
}

   

}
