<?php

namespace App\Controller;

use App\Entity\Product;
use App\Repository\productRepository;
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
    public function test(): Response
    {
        
        dump($productRepository->getFilteredProducts('pant', 0, 150, 'ALL'));
        die();

    }

    /**
     * @Route("/productsList", name="api_productsList", methods={"GET"})
     */
    public function ProductsList(productRepository $productRepository)
    {
        return $this->json($productRepository->findAll(), 200, [], ['groups' => 'product']);
    }


    /**
     * @Route("/DetailProduct", name="api_DetailProduct", methods={"GET"})
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
            $jsonRecu=$request->getContent();
            $product=$serializer->deserialize($jsonRecu, Product::class, 'json');
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
     * @Route("/DeleteProduct", name="api_DeleteProduct", methods={"DELETE"})
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
    * @Route("/UpdateProduct", name="api_UpdateProduct", methods={"PUT"})
    */
    public function UpdateProduct(Request $request, productRepository $productRepository, SerializerInterface $serializer, EntityManagerInterface $em){
        try{
            $jsonRecu=$request->getContent();
            $productReceived=$serializer->deserialize($jsonRecu, Product::class, 'json');

            $id=$request->get("id");
            $productToUpdate=$productRepository->find($id);

            $productToUpdate->setProductName($productReceived->getProductName());
            $productToUpdate->setImage($productReceived->getImage());
            $productToUpdate->setProductPrice($productReceived->getProductPrice());
            $productToUpdate->setDescription($productReceived->getDescription());
            $productToUpdate->setColor($productReceived->getColor());
            $productToUpdate->setMark($productReceived->getMark());
            $productToUpdate->setDiscount($productReceived->getDiscount());
            $productToUpdate->setDisponibility($productReceived->getDisponibility());
            $productToUpdate->setStockQuantity($productReceived->getStockQuantity());
            // dd($id,$productToUpdate);
            $em->persist($productToUpdate);
            $em->flush();
            return $this->json($productToUpdate, 201, [], ['groups' => 'product']);
        }catch(NotEncodableValueException $e){
            return $this->json([
               'status' => 400,
               'message' => $e->getMessage()
            ], 400);
        }
    
    }

}