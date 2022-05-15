<?php

namespace App\Controller;

use App\Entity\Users;
use App\Form\UsersType;
use App\Repository\UsersRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Doctrine\DBAL\Driver\PDO\Exception;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

/**
 * @Route("/Users")
 */
class UsersController extends AbstractController
{
    private $passwordEncoder;
    public function __construct(UserPasswordEncoderInterface $passwordEncoder)
    {
        $this->passwordEncoder = $passwordEncoder;
    }


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
            $user->setPassword($this->passwordEncoder->encodePassword($user, $user->getPassword()));
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

     * @Route ("/Block/{id}", name="app_users_block")
     */
    public function blocker($id,UsersRepository $repository,\Swift_Mailer $mailer){
        $user =$repository->find($id);
        $user ->setCheckAccount('Blocked');
        $em=$this->getDoctrine()->getManager();

        $em->flush();
        $em = $this->getDoctrine()->getManager();
        $u= $user ->getEmail();



        $message = (new \Swift_Message('New'))
            ->setFrom('ggesporttn@gmail.com')
            ->setTo($u)
            ->setSubject('[Etat du compte]')
            ->setBody(
                $this->renderView(
                    'mail/sendi.html.twig',['user'=>$user]),

                'text/html'
            );



        $mailer->send($message);

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
     * @Route("/bot/{id}", name="botzakazikou")
     */
    public function bot( \Swift_Mailer $mailer, UsersRepository $repository, Users $users,$id): Response
    {

        $users = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $u= $users ->getEmail();



        $message = (new \Swift_Message('New'))
            ->setFrom('ggesporttn@gmail.com')
            ->setTo($u)
            ->setSubject('[Votre Conge expire dans 3 jours]')
            ->setBody(
                $this->renderView(
                    'mail/sendi.html.twig',['user'=>$users]),

                'text/html'
            );



        $mailer->send($message);
        return $this->render('mail/request.html.twig',['user'=>$users]);
    }

}

