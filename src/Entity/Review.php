<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\ReviewRepository;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Review
 *
 * @ORM\Table(name="review")
 * @ORM\Entity(repositoryClass="App\Repository\ReviewRepository")
 */
class Review
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("Review")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Groups("Review")
     */
    private $value;

    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Product", inversedBy="reviews")
     * @ORM\JoinColumn(name="product", referencedColumnName="id_product")
     * @Groups("Review")
     */
    private $product;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getValue(): ?int
    {
        return $this->value;
    }

    public function setValue(int $value): self
    {
        $this->value = $value;

        return $this;
    }

    public function getProduct(): ?Product
    {
        return $this->product;
    }

    public function setProduct(?Product $product): self
    {
        $this->product = $product;

        return $this;
    }
}
