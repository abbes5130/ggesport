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
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Gedmo\Sluggable\Util\Urlizer;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Joli\JoliNotif\NotifierFactory;
use Joli\JoliNotif\Notification;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;


/**
 * @Route("/team")
 */
class TeamController extends AbstractController
{

    /**
     * @Route("/", name="app_team_index", methods={"GET"})
     */
    public function index(NormalizerInterface $normalizer): Response
    {
        $repository = $this->getDoctrine()->getRepository(Team::class);
        $teams = $repository->findAll();
        $jsonContent = $normalizer->normalize($teams, 'json', ['groups'=>'post:read']);

        return new Response(json_encode($jsonContent)
        );
    }


    /**
     * @Route("/new", name="app_team_new", methods={"GET", "POST"})
     */
    public function new(Request $request, NormalizerInterface $Normalizer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $team = new Team();
        $team->setTeamName($request->get('team_name'));
        $team->setLogo($request->get('logo'));
        $team->setPlayersNumber($request->get('players_number'));
        $team->setDescription($request->get('description'));
        $em->persist($team);
        $em->flush();
        $jsonContent = $Normalizer->normalize($team, 'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/{idTeam}", name="app_team_show", methods={"GET"})
     */
    public function show(Request $request, $idTeam, NormalizerInterface $normalizer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $team = $em->getRepository(Team::class)->find($idTeam);

        $jsonContent = $normalizer->normalize($team, 'json', ['groups'=>'post:read']);
        return $this->render('test/allteamJSON.html.twig',[
            'data'=> $jsonContent,
        ]);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/{idTeam}/edit", name="app_team_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, $idTeam, NormalizerInterface $Normalizer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $team = $em->getRepository(Team::class)->find($idTeam);
        $team->setTeamName($request->get('team_name'));
        $team->setLogo($request->get('logo'));
        $team->setPlayersNumber($request->get('players_number'));
        $team->setDescription($request->get('description'));
        $em->flush();
        $jsonContent = $Normalizer->normalize($team, 'json',['groups'=>'post:read']);
        return new Response("updated successfully".json_encode($jsonContent));
    }

    /**
     * @Route("/{idTeam}/delete", name="app_team_delete", methods={"GET", "DELETE"})
     */
    public function delete(Request $request, $idTeam, NormalizerInterface $Normalizer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $team = $em->getRepository(Team::class)->find($idTeam);
        $em->remove($team);
        $em->flush();
        $jsonContent = $Normalizer->normalize($team, 'json',['groups'=>'post:read']);
        return new Response("deleted successfully".json_encode($jsonContent));


    }

}