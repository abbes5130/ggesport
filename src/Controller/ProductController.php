<?php

namespace App\Controller;

use App\Entity\Product;
use App\Form\ProductType;
use App\Repository\productRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

/**
 * @Route("/product")
 */
class ProductController extends AbstractController
{

    /**
     * @Route("/user_index", name="app_product_user_index", methods={"GET"})
     */
    public function userIndex(productRepository $productRepository): Response
    {
        return $this->render('product/user_index.html.twig');
    }

    /**
     * @Route("/user_productsList", name="app_product_productsList", methods={"GET"})
     */
    public function userProductsList(productRepository $productRepository): Response
    {
        return $this->render('product/user_productsList.html.twig', [
            'products' => $productRepository->findAll(),
        ]);
    }


    /**
     * @Route("/", name="app_product_index", methods={"GET"})
     */
    public function index(productRepository $productRepository): Response
    {
        // dump($productRepository->findAll());
        // die();
        return $this->render('product/index.html.twig', [
            'products' => $productRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_product_new", methods={"GET", "POST"})
     */
    public function new(Request $request, productRepository $productRepository): Response
    {
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                // $safeFilename = $slugger->slug($originalFilename);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        $this->getParameter('uploads_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                }
                $product->setImage($newFilename);
            }

            $productRepository->add($product);
            return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('product/new.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idProduct}", name="app_product_show", methods={"GET"})
     */
    public function show(Product $product): Response
    {
        return $this->render('product/show.html.twig', [
            'product' => $product
        ]);
    }

    /**
     * @Route("/detail/{idProduct}", name="app_product_user_show", methods={"GET"})
     */
    public function userShow(Product $product): Response
    {
        return $this->render('product/user_show.html.twig', [
            'product' => $product
        ]);
    }

    /**
     * @Route("/{idProduct}/edit", name="app_product_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Product $product, productRepository $productRepository): Response
    {
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $productRepository->add($product);
            return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('product/edit.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idProduct}/delete", name="app_product_delete")
     */
    public function delete(Request $request, Product $product, productRepository $productRepository): Response
    {
        // if ($this->isCsrfTokenValid('delete'.$product->getIdProduct(), $request->request->get('_token'))) {
            $productRepository->remove($product);
        // }

        return $this->redirectToRoute('app_product_index');
    }
}
