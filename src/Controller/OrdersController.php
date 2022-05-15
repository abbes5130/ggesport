<?php

namespace App\Controller;

use App\Repository\OrdersRepository;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

/**
 * @Route("/Orders")
 */
class OrdersController extends AbstractController
{
    /**
     * @Route("/", name="app_Orders_index", methods={"GET", "POST"})
     */
    public function index(Request $request, OrdersRepository $ordersRepository): Response
    {
        dd($ordersRepository->find(3));
        return $this->render('orders/index.html.twig', [
            'orders' => $ordersRepository->findAll(),
        ]);
    }

}
