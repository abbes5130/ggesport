<?php

namespace App\Controller;

use App\Entity\Role;

use App\Form\RoleType;
use App\Form\UsersType;
use App\Repository\RoleRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/Role")
 */
class RoleController extends AbstractController
{
    /**
     * @Route("/", name="app_role_index")
     */
    public function index(RoleRepository $repository): Response
    {
        $role = $repository

            ->findAll();

        return $this->render('role/index.html.twig', [
            'roles' => $role,
        ]);
    }

    /**
     * @Route("/new", name="app_role_new")
     */
    public function new(Request $request): Response
    {
        $role = new Role();
        $form = $this->createForm(RoleType::class, $role);
        //$form->add('ADD',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em=$this->getDoctrine()->getManager();
            $em->persist($role);
            $em->flush();

            return $this->redirectToRoute('app_role_index');
        }

        return $this->render('role/new.html.twig', [
            'role' => $role,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_role_show", methods={"GET"})
     */
    public function show(Role $role): Response
    {
        return $this->render('role/show.html.twig', [
            'role' => $role,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_role_edit")
     */
    public function edit($id,Request $request,RoleRepository $repository): Response
    {
        $role=$repository->find($id);
        $form = $this->createForm(RoleType::class, $role);
       // $form->add('Update',SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em=$this->getDoctrine()->getManager();
            $em->flush();

            return $this->redirectToRoute('app_role_index');
        }

        return $this->render('role/edit.html.twig', [
            'role' => $role,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/delet/{id}", name="app_role_delete")
     */
    public function delete($id,RoleRepository $repository): Response
    {
        $role = $repository
            ->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($role);
        $em->flush();


        return $this->redirectToRoute('app_role_index');
    }
}
