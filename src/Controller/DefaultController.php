<?php

namespace App\Controller;
use App\Services\QrcodeService;
use App\Form\CategoryType;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Category;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CategoryRepository;

class DefaultController extends AbstractController
{
    /**
     * @Route("/default", name="app_default")
     * @param Request $request
     * @param QrcodeService $qrcodeService
     * @return Response
     */
    public function index(CategoryRepository $categoryRepository,Request $request, QrcodeService $qrcodeService): Response
    {
        $category=new Category();
        $qrCode = null;
        $form = $this->createForm(CategoryType::class, null);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $name = $form->get('name')->getData(); 
            //$queryBuilder = $categoryRepository->findEntitiesByString($name);
            // $name->setName($category->getName());

            //$data = $form->getData();

            $qrCode = $qrcodeService->qrcode($name);
        }

        return $this->render('default/index.html.twig', [
            'form' => $form->createView(),
            'qrCode' => $qrCode        ]);
    }
   
  
}
