<?php

namespace App\Controller;

use App\Entity\Review;
use App\Entity\Product;
use App\Form\ProductType;
use App\Services\ProductService;
use App\Repository\ReviewRepository;
use App\Repository\productRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;

/**
 * @Route("/product")
 */
class ProductController extends AbstractController
{

    /**
     * @Route("/test", name="app_test", methods={"GET", "POST"}, options={"expose"=true})
     */
    public function test(productRepository $productRepository, ReviewRepository $reviewRepository, SerializerInterface $serializer): Response
    {
        // $res= $productRepository->getFilteredProducts('ffffff', 0, 50000, 'TShirts');
        // $jsonData= $serializer->serialize($res,'json');
        // return new JsonResponse($jsonData);

        // $p= $productRepository->findproductwithreviews("Pant Men’s Renegade");
        dump($productRepository->getFilteredProducts('pant', 0, 150, 'ALL'));
        die();

    }

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
     * @Route("/", name="app_product_index", methods={"GET"}, options={"expose"=true})
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
            'product' => $product,            
        ]);
    }

    /**
     * @Route("/detail/{idProduct}", name="app_product_user_show", methods={"GET"})
     */
    public function userShow(Product $product, ReviewRepository $reviewRepository, productRepository $productRepository): Response
    {
        $category=$product->getCategory()->getCategoryName();
        // dump($category);
        // die();
        $relatedProducts=$productRepository->getRelatedProducts($category);

        $total_S =$reviewRepository->count(['product' => $product]);

        $count5S =$reviewRepository->count(['product' => $product,
                                            'value' => 5]);
        $count4S =$reviewRepository->count(['product' => $product,
                                            'value' => 4]);
        $count3S =$reviewRepository->count(['product' => $product,
                                            'value' => 3]);
        $count2S =$reviewRepository->count(['product' => $product,
                                            'value' => 2]);
        $count1S =$reviewRepository->count(['product' => $product,
                                            'value' => 1]);
        if($total_S>0){
            $avgReview=(($count5S)*5 + ($count4S)*4 + ($count3S)*3 + ($count2S)*2 + ($count1S))/($total_S);
            
        }
        else{
            $avgReview=0;
        }
        

        return $this->render('product/user_show.html.twig', [
            'product' =>$product,
            'total_S' =>$total_S,
            'count5S' =>$count5S,
            'count4S' =>$count4S,
            'count3S' =>$count3S,
            'count2S' =>$count2S,
            'count1S' =>$count1S,
            'avgReview' => $avgReview,
            'relatedProducts'=>$relatedProducts
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

    /**
     * @Route("/FilteredProducts/filter", name="app_product_FilteredProducts", methods={"GET", "POST"}, options={"expose"=true})
     */
    public function getFilteredProducts(Request $request, SerializerInterface $serializer, productRepository $productRepository ){

        $values = $request->request->get('values');
        $array =  explode(",", $values);
        $searchString=$array[0];
        $minPrice=intval(str_replace(' ','',substr($array[1],3)));
        $maxPrice=intval(str_replace(' ','',substr($array[2],3)));
        $categoryName=$array[3];
        $res = $productRepository->getFilteredProducts($searchString,$minPrice,$maxPrice,$categoryName);
        
        $jsonData= $serializer->serialize($res,'json',['groups'=>'product']);
        return new JsonResponse($jsonData);;

    }

    /**
     * @Route("/review{id}", name="app_product_review", methods={"GET", "POST"})
     */
    public function addReview(int $id, Request $request, productRepository $productRepository, ReviewRepository $reviewRepository, SerializerInterface $serializer){
        $value = $request->request->get('value');

        $product= $productRepository->find($id);
        
        $review = new Review();
        $review->setProduct($product);
        $review->setValue($value);
        $reviewRepository->add($review);

        $nombre= $reviewRepository->count(['product' => $product]);

        return $this->json(['code'=>200,
                            'message'=> 'good job',
                            'count review' => $nombre],200);
        
    }

}
