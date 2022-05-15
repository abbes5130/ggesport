<?php

namespace App\Services;

use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Repository\productRepository;

class CartService{
    
    protected $session;
    protected $productRepository;

    public function __construct(SessionInterface $session, ProductRepository $productRepository)
    {
        $this->session = $session;
        $this->productRepository = $productRepository;
    }

    public function add(int $id) {
        $panier = $this->session->get('panier', []);
        if (!empty($panier[$id])) {
            $panier[$id]++;
        } else {
            $panier[$id] = 1;
        }
        $this->session->set('panier', $panier);
    }
    
    public function remove(int $id) 
    {
        $panier = $this->session->get('panier', []); 
        if (!empty($panier[$id])) {
            unset($panier[$id]);
        }
        $this->session->set('panier', $panier);
    }
    
    public function getFullCart() : array 
    {
        $panier = $this->session->get('panier', []);
        $panierWithData = [];
        foreach ($panier as $id => $quantity) {
            $panierWithData[]=[
                'product' => $this->productRepository->find($id),
                'quantity' => $quantity,
                'totalAmount'=>$this->productRepository->find($id)->getProductPrice() * $quantity,
            ];
        }
        return $panierWithData;
    }

    public function getTotal() : float 
    {
        $total = 0; 
        foreach ($this->getFullCart() as $item) {
            $total += $item['product']->getProductPrice() * $item['quantity'];
        }    

        return $total;
    }
    

    public function increase(int $id)
    {
        $panier = $this->session->get('panier', []);
        if (!empty($panier[$id])) {
            $panier[$id]++;
            $product= $this->productRepository->find($id);
            $totalOneProduct = $panier[$id] * $product->getProductPrice();
        }
        $this->session->set('panier', $panier);
        $total = $this->getTotal();

        $array=array (
            'totalOneProduct'=> $totalOneProduct,
            'total'=>$total,
        );
        return $array;
    }

    public function decrease(int $id)
    {
        $panier = $this->session->get('panier', []); 
        if ($panier[$id]==1) {
            $totalOneProduct=0;
            unset($panier[$id]);
            $msg='deleted';
        }else {
            $panier[$id]--;
            $product= $this->productRepository->find($id);
            $totalOneProduct = $panier[$id] * $product->getProductPrice();
            $msg='decreased';
        }
        $this->session->set('panier', $panier);
        $total = $this->getTotal();
        $array=array (
            'totalOneProduct'=> $totalOneProduct,
            'total'=>$total,
            'msg'=>$msg,
        );
        return $array;
    }


}

