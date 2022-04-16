<?php

namespace App\Controller;
use App\Entity\Team;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
    /**
     * @Route("/home", name="app_home")
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $teams = $entityManager
        ->getRepository(Team::class)
        ->findAll();

        return $this->render('team/index.html.twig', [
            'controller_name' => 'HomeController',
            'teams' => $teams,
        ]);
    }
}
