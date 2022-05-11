<?php

namespace App\Controller;

use App\Entity\Product;
use App\Entity\Category;
use App\Repository\productRepository;
use App\Repository\categoryRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

/**
 * @Route("/api")
 */
class RestController extends AbstractController
{

    /**
     * @Route("/test", name="app_test", methods={"GET", "POST"})
     */
    public function test(categoryRepository $categoryRepository): Response
    {
        
        dd($categoryRepository->findAll());

    }

    /**
     * @Route("/product/getListCategories", name="api_getListCategories")
     */
    public function CategoriesList(categoryRepository $categoryRepository)
    {
        
        return $this->json($categoryRepository->findAll(), 200, [], ['groups' => 'category']);
    }


    /**
     * @Route("/productsList", name="api_productsList")
     */
    public function ProductsList(productRepository $productRepository)
    {
        return $this->json($productRepository->findAll(), 200, [], ['groups' => 'product']);
    }


    /**
     * @Route("/DetailProduct", name="api_DetailProduct")
     */
    public function DetailProduct(Request $request, productRepository $productRepository){
        $id= $request->get("idProduct");
        return $this->json($productRepository->find($id), 200, [], ['groups' => 'product']);
    }


    /**
     * @Route("/PostProduct", name="api_PostProduct", methods={"POST"})
     */
    public function PostProduct(Request $request, SerializerInterface $serializer, EntityManagerInterface $em){
        try{
            $product = new Product();
            $product->setProductName($request->get('name'));
            $product->setProductPrice($request->get('price'));
            $product->setDescription($request->get('description'));
            $product->setColor($request->get('color'));
            $product->setMark($request->get('mark'));
            $product->setStockQuantity($request->get('quantity'));
            $idCategory = $em->find(Category::class, $request->get('idCategory'));
            $product->setCategory($idCategory);
            $em->persist($product);
            $em->flush();
            return $this->json($product, 201, [], ['groups' => 'product']);
        }catch(NotEncodableValueException $e){
            return $this->json([
               'status' => 400,
               'message' => $e->getMessage()
            ], 400);
        }
    
    }

    /**
     * @Route("/DeleteProduct", name="api_DeleteProduct")
     */
    public function DeleteProduct(Request $request, productRepository $productRepository, SerializerInterface $serializer, EntityManagerInterface $em){
        try{
            $id= $request->get("idProduct");
            $product=$productRepository->find($id);
            $em->remove($product);
            $em->flush();
            return $this->json($product, 201, [], ['groups' => 'product']);
        }catch(NotEncodableValueException $e){
            return $this->json([
               'status' => 400,
               'message' => $e->getMessage()
            ], 400);
        }
    
    }

    /**
    * @Route("/UpdateProduct", name="api_UpdateProduct")
    */
    public function UpdateProduct(Request $request, productRepository $productRepository, SerializerInterface $serializer, EntityManagerInterface $em){
        try{
            $id=$request->get("id");
            $product = $productRepository->find($id);
            $product->setProductName($request->get('name'));
            $product->setProductPrice($request->get('price'));
            $product->setDescription($request->get('description'));
            $product->setColor($request->get('color'));
            $product->setMark($request->get('mark'));
            $product->setStockQuantity($request->get('quantity'));
            $idCategory = $em->find(Category::class, $request->get('idCategory'));
            $product->setCategory($idCategory);
    
            $em->flush();
            return $this->json($product, 201, [], ['groups' => 'product']);
        }catch(NotEncodableValueException $e){
            return $this->json([
               'status' => 400,
               'message' => $e->getMessage()
            ], 400);
        }
    
    }

}