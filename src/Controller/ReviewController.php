<?php

namespace App\Controller;

use App\Entity\Review;
use App\Form\ReviewType;
use App\Repository\ReviewRepository;
use App\Repository\productRepository;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

/**
 * @Route("/review")
 */
class ReviewController extends AbstractController
{
    /**
     * @Route("/", name="app_review_index", methods={"GET"})
     */
    public function index(ReviewRepository $reviewRepository, productRepository $productRepository): Response
    {
        // $p=$productRepository->find(27);
        // $r=new Review();
        // $r->setValue(2);
        // $r->setProduct($p);
        // $reviewRepository->add($r);
        // dump($productRepository->findAll());
        // die;
        return $this->render('review/index.html.twig', [
            'reviews' => $reviewRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_review_new", methods={"GET", "POST"})
     */
    public function new(Request $request, ReviewRepository $reviewRepository): Response
    {
        $review = new Review();
        $form = $this->createForm(ReviewType::class, $review);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reviewRepository->add($review);
            return $this->redirectToRoute('app_review_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('review/new.html.twig', [
            'review' => $review,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_review_show", methods={"GET"})
     */
    public function show(Review $review): Response
    {
        return $this->render('review/show.html.twig', [
            'review' => $review,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_review_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Review $review, ReviewRepository $reviewRepository): Response
    {
        $form = $this->createForm(ReviewType::class, $review);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reviewRepository->add($review);
            return $this->redirectToRoute('app_review_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('review/edit.html.twig', [
            'review' => $review,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_review_delete", methods={"POST"})
     */
    public function delete(Request $request, Review $review, ReviewRepository $reviewRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$review->getId(), $request->request->get('_token'))) {
            $reviewRepository->remove($review);
        }

        return $this->redirectToRoute('app_review_index', [], Response::HTTP_SEE_OTHER);
    }
}
