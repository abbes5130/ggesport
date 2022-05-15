<?php

namespace App\Controller;

use App\Entity\Role;
use App\Entity\Users;
use App\Form\UsersType;
use App\Repository\UsersRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoder;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer as NormalizerObjectNormalizer;
use Symfony\Polyfill\Intl\Normalizer\Normalizer;


class ViewsController extends AbstractController
{
    /**
     * @Route("/hom", name="hom")
     */
    public function hom()
    {
        return $this->render('base2.html.twig');
    }

    /**
     * @Route("/stat", name="stat")
     */
    public function statistique(UsersRepository $repo): Response
    {


        $user = $repo->findAll();
        $em = $this->getDoctrine()->getManager();

        $nbAdmin = 0;
        $nbResponsable = 0;
        $nbMembre = 0;

        foreach ($user as $user) {
            if ($user->getIdRole()->getRolename() == "Administrateur")  :

                $nbAdmin += 1;
            elseif ($user->getIdRole()->getRolename() == 'Responsable') :
                $nbResponsable += 1;
            else:
                $nbMembre += 1;
            endif;
        }

        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['Type', 'nombres'],
                ['Admin', $nbAdmin],
                ['Responsables', $nbResponsable],
                ['Membre', $nbMembre]
            ]
        );
        $pieChart->getOptions()->setColors(['#2B54CC', '#FF0000']);

        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('views/stat.html.twig', array('piechart' => $pieChart));

    }

    /**
     * @Route ("/usersjson" , name="UsersJson")
     */
    public function AllUsers(NormalizerInterface $Normalizer, SerializerInterface $serializer, UsersRepository $repository)
    {
        $user = $repository->findAll();

        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'Users']);
        dump($jsonContent);
        return $this->render('users/allUsersJSON.html.twig', ['data' => $jsonContent]);
        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route ("/usersjsonid/{id}" , name="UsersJsonIdjjj")
     */
    public function AllUsersbyid(NormalizerInterface $Normalizer, UsersRepository $repository, $id)
    {
        $user = $repository->find($id);

        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'Users']);

        return $this->render('views/UsersId.html.twig', ['data' => $jsonContent]);
        return new Response(json_encode($jsonContent));

    }


    /**
     * @Route ("/udelet/{id}" , name="UsersJsonIdkkkk")
     */
    public function udelete(NormalizerInterface $Normalizer, UsersRepository $repository, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $repository->find($id);
        $em->remove($user);
        $em->flush();

        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'Users']);


        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route ("/singupActionJson" , name="UsersJsonSingUp")
     */
    public function signupAction(NormalizerInterface $Normalizer, UserPasswordEncoderInterface $passwordEncoder, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = new Users();

        $user->setFirstname($request->get('firstname'));
        $user->setLastname($request->get('lastname'));
        $user->setEmail($request->get('email'));
        $user->setPassword($passwordEncoder->encodePassword($user,$request->get('password')));
        $user->setPhoneNumber($request->get('phone_number'));
        $role = $em->getRepository(Role::class)->find(3);

        $user->setIdRole($role);
        $user->setCheckAccount('Not_Blocked');

        $em->persist($user);
        $em->flush();
        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'Users']);
        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route ("/singinActionJson" , name="api_login")
     */
    public function signinAction(Request $request, NormalizerInterface $Normalizer)
    {


        $email = $request->query->get("email");
        $password = $request->query->get("password");

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(Users::class)->findOneBy(['email' => $email]);

        if ($user) {
            if (password_verify($password, $user->getPassword())) {

                $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'Users']);
                return new Response(json_encode($jsonContent));
            } else {
                return new Response("password incorrect");
            }
        } else {
            return new Response("user not found");
        }
    }

    /**
     * @Route ("/EditProfileJson" , name="UsersJsonEditProfile")
     */
    public function editJson(NormalizerInterface $Normalizer, UserPasswordEncoderInterface $passwordEncoder, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $id = $request->query->get('id_user');

        $user = new Users();
        $user = $em->getRepository(Users::class)->find($id);
        $user->setFirstname($request->get('firstname'));
        $user->setLastname($request->get('lastname'));
        $user->setEmail($request->get('email'));
        $user->setPassword($passwordEncoder->encodePassword($user,$request->get('password')));
        $user->setPhoneNumber($request->get('phone_number'));
        $role = $em->getRepository(Role::class)->find(3);

        $user->setIdRole($role);
        $user->setCheckAccount('Not_Blocked');


        $em->flush();
        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'Users']);
        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/getPasswordByEmail", name="app_passwordJson")
     */
    public function getPassswordByEmail(Request $request)
    {
        $email = $request->get('email');
        $user = $this->getDoctrine()->getManager()->getRepository(Users::class)->findOneBy(['email' => $email]);
        if ($user) {
            $password = $user->getPassword();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($password);

            return new JsonResponse($formatted);
        }
            return new Response("user not found");
        }

}
