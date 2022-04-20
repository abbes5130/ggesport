<?php

namespace App\Controller;
use App\Repository\NewsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
   /**
     * @Route("/", name="home")
     */
    public function index(NewsRepository $NewsRepository): Response
    {
        return $this->render('news/home/index.html.twig', [
            'listNews' => $NewsRepository->findAll(),
        ]);
    }
}
