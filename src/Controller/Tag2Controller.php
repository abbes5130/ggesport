<?php

namespace App\Controller;

use App\Entity\Tag;
use App\Form\Tag1Type;
use App\Repository\TagRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/tag2")
 */
class Tag2Controller extends AbstractController
{
    /**
     * @Route("/", name="app_tag2_index", methods={"GET"})
     */
    public function index(TagRepository $tagRepository): Response
    {
        return $this->render('tag2/index.html.twig', [
            'tags' => $tagRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_tag2_new", methods={"GET", "POST"})
     */
    public function new(Request $request, TagRepository $tagRepository): Response
    {
        $tag = new Tag();
        $form = $this->createForm(Tag1Type::class, $tag);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $tagRepository->add($tag);
            return $this->redirectToRoute('app_tag2_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('tag2/new.html.twig', [
            'tag' => $tag,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_tag2_show", methods={"GET"})
     */
    public function show(Tag $tag): Response
    {
        return $this->render('tag2/show.html.twig', [
            'tag' => $tag,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_tag2_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Tag $tag, TagRepository $tagRepository): Response
    {
        $form = $this->createForm(Tag1Type::class, $tag);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $tagRepository->add($tag);
            return $this->redirectToRoute('app_tag2_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('tag2/edit.html.twig', [
            'tag' => $tag,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_tag2_delete", methods={"POST"})
     */
    public function delete(Request $request, Tag $tag, TagRepository $tagRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$tag->getId(), $request->request->get('_token'))) {
            $tagRepository->remove($tag);
        }

        return $this->redirectToRoute('app_tag2_index', [], Response::HTTP_SEE_OTHER);
    }
}
