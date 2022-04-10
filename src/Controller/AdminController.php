<?php

namespace App\Controller;

use App\Entity\Matches;
use App\Form\MatchesType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
/**
 * @Route("/admin", name="app_admin")
 */
class AdminController extends AbstractController
{

    public function index(): Response
    {
        return $this->render('/back_base.html.twig', [
            'controller_name' => 'AdminController',
        ]);
    }


}
