<?php

namespace App\Controller;

use App\Entity\Team;
use App\Entity\Player;

use App\Form\TeamType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/team")
 */
class TeamController extends AbstractController
{
    /**
     * @Route("/", name="app_team_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $teams = $entityManager
            ->getRepository(Team::class)
            ->findAll();
            $players = $entityManager
            ->getRepository(Player::class)
            ->findAll();    

        return $this->render('team/index.html.twig', [
            'teams' => $teams,
            'players' => $players,

        ]);
    }

    /**
     * @Route("/new", name="app_team_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $team = new Team();
        $form = $this->createForm(TeamType::class, $team);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($team);
            $entityManager->flush();

            return $this->redirectToRoute('app_team_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('team/new.html.twig', [
            'team' => $team,
            'form' => $form->createView(),
            
        ]);
    }

    /**
     * @Route("/{idTeam}", name="app_team_show", methods={"GET"})
     */
    public function show(Team $team, EntityManagerInterface $entityManager): Response
    {
        $players = $entityManager
        ->getRepository(Player::class)
        ->findAll();    
        return $this->render('team/single-team.html.twig', [
            'team' => $team,
            'players' => $players,
        ]);
    }

    /**
     * @Route("/{idTeam}/edit", name="app_team_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Team $team, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TeamType::class, $team);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_team_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('team/edit.html.twig', [
            'team' => $team,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idTeam}", name="app_team_delete", methods={"POST"})
     */
    public function delete(Request $request, Team $team, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$team->getIdTeam(), $request->request->get('_token'))) {
            $entityManager->remove($team);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_team_index', [], Response::HTTP_SEE_OTHER);
    }
}
