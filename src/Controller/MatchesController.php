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
 * @Route("/matches")
 */
class MatchesController extends AbstractController
{
    /**
     * @Route("/", name="app_matches_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $matches = $entityManager
            ->getRepository(Matches::class)
            ->findAll();

        return $this->render('matches/match_list.html.twig', [
            'matches' => $matches,
        ]);
    }


    /**
     * @Route("/matchDetail/{idMatch}", name="app_matches_detail_show", methods={"GET"})
     */
    public function showDetail(Matches $match): Response
    {

        return $this->render('matches/match_detail.html.twig', [
            'match' => $match,
        ]);
    }

}
