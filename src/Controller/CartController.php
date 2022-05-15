<?php

namespace App\Controller;

use DateTime;
use App\Entity\Users;
use App\Entity\Orders;
use App\Entity\ProductOrder;
use App\Services\CartService;
use App\Repository\userRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\SerializerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

class CartController extends AbstractController
{

    /**
     * @Route("/cart/test", name="test_cart")
     */
    public function test(CartService $cartService): Response
    {
        // dump($cartService->decrease(31));
        // die();
        //return new Response($msg);
    }


    /**
     * @Route("/cart", name="app_cart")
     */
    public function index(CartService $cartService): Response
    {
        // dump($cartService->increase(31));
        // die();
        return $this->render('cart/index.html.twig', [
            'items' => $cartService->getFullCart(),
            'total' => $cartService-> getTotal()
        ]);
    }

    /**
     * @Route("/cart/add/{id}", name="cart_add")
     */
    public function add($id, CartService $cartService)
    {
        $cartService->add($id);
        return $this->redirectToRoute("app_cart");
    }

    /**
     * @Route("/cart/remove/{id}", name="cart_remove")
     */
    public function remove($id, CartService $cartService)
    {
        $cartService->remove($id);
        return $this->redirectToRoute("app_cart");
    }

    /**
     * @Route("/cart/increase", name="increase")
     */
    public function increase(Request $request, CartService $cartService, SerializerInterface $serializer)
    {
        $id = $request->request->get('id');
        $res= $cartService->increase($id);
        $jsonData= $serializer->serialize($res,'json');
        return new JsonResponse($jsonData);
        
    }

    /**
     * @Route("/cart/decrease", name="decrease")
     */
    public function decrease(Request $request, CartService $cartService, SerializerInterface $serializer){

        $id = $request->request->get('id');
        $res= $cartService->decrease($id);
        $jsonData= $serializer->serialize($res,'json');
        return new JsonResponse($jsonData);


        // $array = array(
        //     array(
        //         'name' => 'name1',
        //         'address' => 'addres1',
        //     ),
        //       array(
        //         'name' => 'name2',
        //         'address' => 'addres2',
        //     ),
        // );
        // $jsonData= $serializer->serialize($array,'json');
        // return new JsonResponse($jsonData);

    }

    /**
     * @Route("/checkout", name="checkout")
     */
    public function checkout(CartService $cartService)
    {
        // dd($cartService->getFullCart());

        return $this->render('cart/checkout.html.twig', [
            'items' => $cartService->getFullCart(),
            'total' => $cartService-> getTotal()
        ]);
    }

    /**
     * @Route("/order", name="order", methods={"GET"})
     */
    public function order(Request $request, CartService $cartService, EntityManagerInterface $em, userRepository $userRepo)
    {
        $productsInCart=$cartService->getFullCart();
        $total=$cartService->getTotal();

        $order = new Orders();
        $order->setIdUser(1);
        $order->setOrderDate(new DateTime('now'));
        $order->setOrderTime(new DateTime('now'));
        $order->setState(true);
        $em->persist($order);
        $em->flush();

        
        foreach ($productsInCart as $p) {
            $objectP=(object)$p;
            $productOrder= new ProductOrder();
            $productOrder->setIdProduct($objectP->product);
            $productOrder->setQuantity($objectP->quantity);
            $productOrder->setIdOrder($order);
            $em->persist($productOrder);
            $em->flush();
        }

        return $this->redirectToRoute("app_product_productsList");
        
    }

}