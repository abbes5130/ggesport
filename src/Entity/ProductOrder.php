<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ProductOrder
 *
 * @ORM\Table(name="product_order", indexes={@ORM\Index(name="product_order_fk1", columns={"id_product"}), @ORM\Index(name="product_order_fk0", columns={"id_order"})})
 * @ORM\Entity
 * 
 * @ORM\Entity(repositoryClass="App\Repository\ProductOrderRepository")
 */
class ProductOrder
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_product_order", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idProductOrder;

    /**
     * @var int|null
     *
     * @ORM\Column(name="quantity", type="integer", nullable=true)
     */
    private $quantity;

    /**
     * @var \Product
     *
     * @ORM\ManyToOne(targetEntity="Product")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_product", referencedColumnName="id_product")
     * })
     */
    private $idProduct;

    /**
     * @var \Orders
     *
     * @ORM\ManyToOne(targetEntity="Orders")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_order", referencedColumnName="id_order")
     * })
     */
    private $idOrder;

    public function getIdProductOrder(): ?int
    {
        return $this->idProductOrder;
    }

    public function getQuantity(): ?int
    {
        return $this->quantity;
    }

    public function setQuantity(?int $quantity): self
    {
        $this->quantity = $quantity;

        return $this;
    }

    public function getIdProduct(): ?Product
    {
        return $this->idProduct;
    }

    public function setIdProduct(?Product $idProduct): self
    {
        $this->idProduct = $idProduct;

        return $this;
    }

    public function getIdOrder(): ?Orders
    {
        return $this->idOrder;
    }

    public function setIdOrder(?Orders $idOrder): self
    {
        $this->idOrder = $idOrder;

        return $this;
    }


}
