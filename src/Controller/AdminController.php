<?php

namespace App\Controller;

use App\Form\PlayerType;
use App\Form\TeamType;
use App\Form\GameType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Team;
use App\Entity\Player;
use App\Entity\Game;



/**
 * @Route("/admin")
 */
class AdminController extends AbstractController
{
    /**
     * @Route("/", name="app_admin")
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
       
        return $this->render('admin/index.html.twig', [

            'controller_name' => 'AdminController',
            
        ]);
    } 

 
    /**
     * @Route("/team", name="app_admin_allteam")
     */
    public function index2(EntityManagerInterface $entityManager): Response
    {
        $teams = $entityManager
        ->getRepository(Team::class)
        ->findAll();
        return $this->render('team/allTeams.html.twig', [

            'controller_name' => 'AdminController',
            'teams'=>$teams,
        ]);
    }
    /**
     * @Route("/player", name="app_admin_allplayer")
     */
    public function index3(EntityManagerInterface $entityManager): Response
    {
        $players = $entityManager
        ->getRepository(Player::class)
        ->findAll();
        return $this->render('player/index.html.twig', [

            'controller_name' => 'AdminController',
            'players'=>$players,
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






    //players section


       /**
     * @Route("/player/new", name="app_player_new_admin", methods={"GET", "POST"})
     */
    public function new2(Request $request, EntityManagerInterface $entityManager): Response
    {
        $player = new Player();

        $form = $this->createForm(PlayerType::class, $player);
        $form->handleRequest($request);
        $teams = $entityManager
        ->getRepository(Team::class)
        ->findAll();
        $players = $entityManager
        ->getRepository(Player::class)
        ->findAll();


        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($player);
            $entityManager->flush();

            return $this->redirectToRoute('app_admin_allplayer', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('player/new.html.twig', [
            'player' => $player,
            'teams' => $teams,
            'players' => $players,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/player/{idPlayer}", name="app_player_show_admin", methods={"GET"})
     */
    public function show2(Player $player): Response
    {
        return $this->render('player/show.html.twig', [
            'player' => $player,
        ]);
    }

    /**
     * @Route("/player/{idPlayer}/edit", name="app_player_edit_admin", methods={"GET", "POST"})
     */
    public function edit2(Request $request, Player $player, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PlayerType::class, $player);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_admin_allplayer', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('player/edit.html.twig', [
            'player' => $player,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/player/{idPlayer}", name="app_player_delete_admin", methods={"POST"})
     */
    public function delete2(Request $request, Player $player, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$player->getIdPlayer(), $request->request->get('_token'))) {
            $entityManager->remove($player);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_player_index', [], Response::HTTP_SEE_OTHER);
    }


    //end of players controllers


    //game section


     /**
     * @Route("/game", name="app_game_index_admin", methods={"GET"})
     */
    public function index4(EntityManagerInterface $entityManager): Response
    {
        $games = $entityManager
            ->getRepository(Game::class)
            ->findAll();
            $teams = $entityManager
            ->getRepository(Team::class)
            ->findAll();

            $players = $entityManager
            ->getRepository(Player::class)
            ->findAll();

        return $this->render('game/index.html.twig', [
            'games' => $games,
            'teams' => $teams,
            'players' => $players,

        ]);
    }

    /**
     * @Route("/game/new", name="app_game_new_admin", methods={"GET", "POST"})
     */
    public function new4(Request $request, EntityManagerInterface $entityManager): Response
    {
        $game = new Game();
        $form = $this->createForm(GameType::class, $game);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($game);
            $entityManager->flush();

            return $this->redirectToRoute('app_game_index_admin', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('game/new.html.twig', [
            'game' => $game,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/game/{idGame}", name="app_game_show_admin", methods={"GET"})
     */
    public function show4(Game $game): Response
    {
        return $this->render('game/show.html.twig', [
            'game' => $game,
        ]);
    }

    /**
     * @Route("/game/{idGame}/edit", name="app_game_edit_admin", methods={"GET", "POST"})
     */
    public function edit4(Request $request, Game $game, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(GameType::class, $game);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_game_index_admin', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('game/edit.html.twig', [
            'game' => $game,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/game/{idGame}", name="app_game_delete_admin", methods={"POST"})
     */
    public function delete4(Request $request, Game $game, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$game->getIdGame(), $request->request->get('_token'))) {
            $entityManager->remove($game);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_game_index_admin', [], Response::HTTP_SEE_OTHER);
    }
}
