<?php
namespace App\Controller;

use App\Entity\Role;
use App\Entity\Users;
use App\Form\RegistrationType;
use App\Form\UsersType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class RegistrationController extends AbstractController
{
    private $passwordEncoder;

    public function __construct(UserPasswordEncoderInterface $passwordEncoder)
    {
        $this->passwordEncoder = $passwordEncoder;
    }

    /**
     * @Route("/registration", name="registration")
     */
    public function index(Request $request)
    {
        $user = new Users();
        //$role = new Role(3);


        $form = $this->createForm(RegistrationType::class, $user);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // Encode the new users password
            $user->setPassword($this->passwordEncoder->encodePassword($user, $user->getPassword()));
            $user->setCheckAccount('Not_blocked');
            // Set their role

           //$user->setIdRole($role);

            // Save
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            return $this->redirectToRoute('app_users_index');
        }

        return $this->render('registration/register.html.twig', [
            'forme' => $form->createView(),
        ]);
    }

    /**
     * @
     * @Route ("/connexion" , name="error")
     */
    public function login(){


            return $this->render('registration/error.html.twig');



    }
    /**
     * @Route ("/deconnexion" , name="logoute")
     */
    public function logout(){}
}