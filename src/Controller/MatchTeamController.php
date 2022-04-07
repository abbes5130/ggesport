<?php

namespace App\Controller;

use App\Entity\MatchTeam;
use App\Form\MatchTeamType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/match/team")
 */
class MatchTeamController extends AbstractController
{
    /**
     * @Route("/", name="app_match_team_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $matchTeams = $entityManager
            ->getRepository(MatchTeam::class)
            ->findAll();

        return $this->render('match_team/index.html.twig', [
            'match_teams' => $matchTeams,
        ]);
    }

    /**
     * @Route("/new", name="app_match_team_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $matchTeam = new MatchTeam();
        $form = $this->createForm(MatchTeamType::class, $matchTeam);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($matchTeam);
            $entityManager->flush();

            return $this->redirectToRoute('app_match_team_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('match_team/new.html.twig', [
            'match_team' => $matchTeam,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idMatchTeam}", name="app_match_team_show", methods={"GET"})
     */
    public function show(MatchTeam $matchTeam): Response
    {
        return $this->render('match_team/show.html.twig', [
            'match_team' => $matchTeam,
        ]);
    }

    /**
     * @Route("/{idMatchTeam}/edit", name="app_match_team_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, MatchTeam $matchTeam, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(MatchTeamType::class, $matchTeam);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_match_team_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('match_team/edit.html.twig', [
            'match_team' => $matchTeam,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idMatchTeam}", name="app_match_team_delete", methods={"POST"})
     */
    public function delete(Request $request, MatchTeam $matchTeam, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$matchTeam->getIdMatchTeam(), $request->request->get('_token'))) {
            $entityManager->remove($matchTeam);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_match_team_index', [], Response::HTTP_SEE_OTHER);
    }
}
