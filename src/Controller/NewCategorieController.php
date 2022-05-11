<?php

namespace App\Controller;

use App\Entity\Newcategorie;
use App\Form\NewcategorieType;
use App\Repository\NewcategorieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/new/categorie")
 */
class NewCategorieController extends AbstractController
{
    /**
     * @Route("/", name="app_new_categorie_index", methods={"GET"})
     */
    public function index(NewcategorieRepository $newcategorieRepository): Response
    {
        return $this->render('new_categorie/index.html.twig', [
            'newcategories' => $newcategorieRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_new_categorie_new", methods={"GET", "POST"})
     */
    public function new(Request $request, NewcategorieRepository $newcategorieRepository): Response
    {
        $newcategorie = new Newcategorie();
        $form = $this->createForm(NewcategorieType::class, $newcategorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $newcategorieRepository->add($newcategorie);
            return $this->redirectToRoute('app_new_categorie_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('new_categorie/new.html.twig', [
            'newcategorie' => $newcategorie,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_new_categorie_show", methods={"GET"})
     */
    public function show(Newcategorie $newcategorie): Response
    {
        return $this->render('new_categorie/show.html.twig', [
            'newcategorie' => $newcategorie,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_new_categorie_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Newcategorie $newcategorie, NewcategorieRepository $newcategorieRepository): Response
    {
        $form = $this->createForm(NewcategorieType::class, $newcategorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $newcategorieRepository->add($newcategorie);
            return $this->redirectToRoute('app_new_categorie_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('new_categorie/edit.html.twig', [
            'newcategorie' => $newcategorie,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_new_categorie_delete", methods={"POST"})
     */
    public function delete(Request $request, Newcategorie $newcategorie, NewcategorieRepository $newcategorieRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$newcategorie->getId(), $request->request->get('_token'))) {
            $newcategorieRepository->remove($newcategorie);
        }

        return $this->redirectToRoute('app_new_categorie_index', [], Response::HTTP_SEE_OTHER);
    }
}
