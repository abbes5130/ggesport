<?php

namespace App\Controller;

use App\Entity\Users;
use App\Form\UsersType;
use App\Repository\UsersRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

/**
 * @Route("/Users")
 */
class UsersController extends AbstractController
{
    /**
     * @Route("/", name="app_users_index")
     */
    public function index(UsersRepository $repository): Response
    {
        $users = $repository

            ->findAll();

        return $this->render('users/index.html.twig', [
            'users' => $users,
        ]);
    }

    /**
     * @Route("/new", name="app_users_new")
     */
    public function new(Request $request): Response
    {
        $user = new Users();
        $form = $this->createForm(UsersType::class, $user);
       // $form->add('ADD',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em=$this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            return $this->redirectToRoute('app_users_index');
        }

        return $this->render('users/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_users_show", methods={"GET"})
     *
     */
    public function show(Users $user): Response
    {
        return $this->render('users/show.html.twig', [
            'user' => $user,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_users_edit")
     */
    public function edit($id,Request $request, Users $user, UsersRepository $repository): Response
    {
        $user=$repository->find($id);
        $form = $this->createForm(UsersType::class, $user);
       // $form->add('Update',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em=$this->getDoctrine()->getManager();
              $em->flush();

            return $this->redirectToRoute('app_users_index');
        }

        return $this->render('users/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/deletee/{id}", name="app_users_delete")
     */
    public function delete($id,UsersRepository $repository): Response
    {
        $user = $repository
            ->find($id);
        $em=$this->getDoctrine()->getManager();
            $em->remove($user);
            $em->flush();


        return $this->redirectToRoute('app_users_index');
    }

    /**
     *
     * @Route("/trie", name="trie")
     * @ParamConverter("Users", class="App:Users")
     *
     *
     */
    public function orderUser(UsersRepository $repository){

        $user =$repository->OrderBynom();

        return $this->render('users/index.html.twig', [
            'user' => $user
        ]);

    }

    /**
     *
     * @Route("/recherche", name="recherche")
     *
     *
     *
     */
    public function recherche(UsersRepository $repository,Request $request){
        $data=$request->get('search');
        $user=$repository->findBy(['lastname'=>$data]);
        return $this->render('users/index.html.twig',['users'=>$user]);

    }

    /**

     * @Route ("/Block/{id}", name="app_users_block")
     */
    public function blocker($id,UsersRepository $repository){
        $user =$repository->find($id);
        $user ->setCheckAccount('Blocked');
        $em=$this->getDoctrine()->getManager();

        $em->flush();

        return $this->redirectToRoute('app_users_index');

    }
    /**

     * @Route ("/deBlock/{id}", name="app_users_deblocker")
     */
    public function deblocker($id,UsersRepository $repository){
        $user =$repository->find($id);
        $user ->setCheckAccount('Not_blocked');
        $em=$this->getDoctrine()->getManager();

        $em->flush();

        return $this->redirectToRoute('app_users_index');

    }


    /**

     * @Route ("/home", name="home")
     */
    public function home_page(){
        return $this->render('users/home.html.twig', [
            'users' => $this->getUser(),
        ]);
    }
}

