<?php

namespace App\Controller;

use App\Entity\Matches;
use App\Entity\Team;
use App\Form\MatchesType;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use phpDocumentor\Reflection\DocBlock\Serializer;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Constraints\Time;

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


    /**
     * @Route("/getListTeam", name="app_matches_ListTeam_Mobile")
     */

    public function getListTeam(Request $request, NormalizerInterface $normalizer):Response
    {
        $repo=$this->getDoctrine()->getRepository(Team::class);
        $team=$repo->findAll();
        $jsonContent=$normalizer->normalize($team,'json',['groups'=>'teams']);

        return new Response(json_encode($jsonContent));



    }


    /**
     * @Route("/newMatchMobile", name="app_matches_new_Mobile")
     */

    public function addMatchMobile(Request $request, NormalizerInterface $normalizer):Response
    {
        $match = new Matches();
        $em = $this->getDoctrine()->getManager();
        $idTeam1 = $em->find(Team::class, $request->get('idTeam1'));;
        $idTeam2 = $em->find(Team::class, $request->get('idTeam2'));;





        $location = $request->get('location');
        $match->setLocation($location);

        $match->setLink($request->get('link'));
        $match->setTime(new DateTime('now'));
        $match->setDate(new DateTime('now'));
        $match->setNbSeats($request->get('nb_seats'));
        $match->setPrice($request->get('price'));
        $match->setIdTeam1($idTeam1);
        $match->setIdTeam2($idTeam2);

        $em->persist($match);
        $em->flush();

        $jsonContent = $normalizer->normalize($match,'json',['groups'=>'matches']);


        return new Response(json_encode($jsonContent));



    }

    /**
     * @Route("/allMatchMobile", name="app_matches_Display_Mobile", methods={"GET"})
     */
    public function allMatchMobile(NormalizerInterface $normalizer)
    {
        $repo=$this->getDoctrine()->getRepository(Matches::class);
        $match=$repo->findAll();
        $jsonContent=$normalizer->normalize($match,'json',['groups'=>'matches']);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/matchDetailMobile/{id}", name="app_matches_Detail_Mobile", methods={"GET"})
     */
    public function MatchDetailMobile(NormalizerInterface $normalizer,Request $request,$id)
    {
        $em=$this->getDoctrine()->getmanager();
        $match=$em->getRepository(Matches::class)->find($id);
        $jsonContent=$normalizer->normalize($match,'json',['groups'=>'matches']);

        return new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/matchUpdateMobile/{id}", name="app_matches_Update_Mobile", methods={"GET", "POST"})
     */
    public function MatchUpdateMobile(NormalizerInterface $normalizer,Request $request,$id)
    {


        $em = $this->getDoctrine()->getManager();
        $match=$em->getRepository(Matches::class)->find($id);
        $idTeam1 = $em->find(Team::class, $request->get('idTeam1'));;
        $idTeam2 = $em->find(Team::class, $request->get('idTeam2'));;


        $location = $request->get('location');
        $match->setLocation($location);

        $match->setLink($request->get('link'));
        $match->setTime(new DateTime('now'));
        $match->setDate(new DateTime('now'));
        $match->setNbSeats($request->get('nb_seats'));
        $match->setPrice($request->get('price'));
        $match->setIdTeam1($idTeam1);
        $match->setIdTeam2($idTeam2);

        $em->flush();

        $jsonContent = $normalizer->normalize($match,'json',['groups'=>'matches']);


        return new Response(json_encode($jsonContent));


    }

    /**
     * @Route("/matchDeleteMobile/{id}", name="app_matches_Delete_Mobile")
     */
    public function MatchDeleteMobile(NormalizerInterface $normalizer,Request $request,$id)
    {
        $em=$this->getDoctrine()->getmanager();
        $match=$em->getRepository(Matches::class)->find($id);
        $em->remove($match);
        $em->flush();
        $jsonContent=$normalizer->normalize($match,'json',['groups'=>'matches']);

        return new Response("MatchDeleted Successfully".json_encode($jsonContent));
    }


}
