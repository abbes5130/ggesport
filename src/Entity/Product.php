<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\productRepository;
use Doctrine\Common\Collections\Collection;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Product
 *
 * @ORM\Table(name="product", indexes={@ORM\Index(name="product_fk0", columns={"category"})})
 * @ORM\Entity(repositoryClass="App\Repository\productRepository")
 */
class Product
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_product", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("product")
     */
    private $idProduct;

    /**
     * @var string|null
     * 
     * @Assert\NotBlank{
     *      message = "The Product name cannot be null"
     * }
     * 
     * @Assert\Length(
     *      min = 3,
     *      max = 20,
     *      minMessage = "The Product name must be at least {{ limit }} characters long",
     *      maxMessage = "The Product name cannot be longer than {{ limit }} characters"
     * )
     * @Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="The Product name cannot contain a number"
     * )
     * @Assert\Regex(
     *     pattern="/\W/",
     *     match=false,
     *     message="The Product name cannot contain special characters"
     * )
     * 
     * @ORM\Column(name="product_name", type="string", length=255, nullable=true)
     * @Groups("product")
     */
    private $productName;

    /**
     * @var string|null
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=true)
     * @Groups("product")
     */
    private $image;

    /**
     * @var float|null
     * 
     * @Assert\NotBlank{
     *      message = "The Product price cannot be null"
     * }
     * 
     * @Assert\GreaterThan(
     *     value = 0,
     *     message="The Product price cannot be less than or equal to 0 DT"
     * )
     * @ORM\Column(name="product_price", type="float", precision=10, scale=0, nullable=true)
     * @Groups("product")
     */
    private $productPrice;

    /**
     * @var string|null
     * 
     * @Assert\Length(
     *      min = 10,
     *      max = 250,
     *      minMessage = "The Product description must be at least {{ limit }} characters long",
     *      maxMessage = "The Product description cannot be longer than {{ limit }} characters"
     * )
     * 
     * @ORM\Column(name="description", type="text", length=65535, nullable=true)
     * @Groups("product")
     */
    private $description;

    /**
     * @var string|null
     * 
     * @Assert\Length(
     *      min = 3,
     *      max = 15,
     *      minMessage = "The Product color must be at least {{ limit }} characters long",
     *      maxMessage = "The Product color cannot be longer than {{ limit }} characters"
     * )
     * 
     * @ORM\Column(name="color", type="string", length=255, nullable=true)
     * @Groups("product")
     */
    private $color;

    /**
     * @var string|null
     * 
     * @Assert\Length(
     *      min = 3,
     *      max = 15,
     *      minMessage = "The Product mark must be at least {{ limit }} characters long",
     *      maxMessage = "The Product mark cannot be longer than {{ limit }} characters"
     * )
     * 
     * @ORM\Column(name="mark", type="string", length=255, nullable=true)
     * @Groups("product")
     */
    private $mark;

    /**
     * @var int|null
     *
     * @ORM\Column(name="discount", type="integer", nullable=true)
     * @Groups("product")
     */
    private $discount;

    /**
     * @var bool|null
     *
     * @ORM\Column(name="disponibility", type="boolean", nullable=true)
     * @Groups("product")
     */
    private $disponibility;

    /**
     * @var int|null
     *
     * @Assert\NotBlank{
     *      message = "The Product quantity cannot be null"
     * }
     * 
     * @ORM\Column(name="stock_quantity", type="integer", nullable=true)
     * @Groups("product")
     */
    private $stockQuantity;

    /**
     * @var \Category
     *
     * @ORM\ManyToOne(targetEntity="Category", inversedBy="products")
     * @ORM\JoinColumn(name="category", referencedColumnName="id_category")
     * @Groups("product")
     */
    private $category;

    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Review", mappedBy="product")
     */
    private $reviews;

    public function __construct()
    {
        $this->reviews = new ArrayCollection();
    }

    public function getIdProduct(): ?int
    {
        return $this->idProduct;
    }

    public function getProductName(): ?string
    {
        return $this->productName;
    }

    public function setProductName(?string $productName): self
    {
        $this->productName = $productName;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getProductPrice(): ?float
    {
        return $this->productPrice;
    }

    public function setProductPrice(?float $productPrice): self
    {
        $this->productPrice = $productPrice;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getColor(): ?string
    {
        return $this->color;
    }

    public function setColor(?string $color): self
    {
        $this->color = $color;

        return $this;
    }

    public function getMark(): ?string
    {
        return $this->mark;
    }

    public function setMark(?string $mark): self
    {
        $this->mark = $mark;

        return $this;
    }

    public function getDiscount(): ?int
    {
        return $this->discount;
    }

    public function setDiscount(?int $discount): self
    {
        $this->discount = $discount;

        return $this;
    }

    public function getDisponibility(): ?bool
    {
        return $this->disponibility;
    }

    public function setDisponibility(?bool $disponibility): self
    {
        $this->disponibility = $disponibility;

        return $this;
    }

    public function getStockQuantity(): ?int
    {
        return $this->stockQuantity;
    }

    public function setStockQuantity(?int $stockQuantity): self
    {
        $this->stockQuantity = $stockQuantity;

        return $this;
    }

    public function getCategory(): ?Category
    {
        return $this->category;
    }

    public function setCategory(?Category $category): self
    {
        $this->category = $category;

        return $this;
    }

    /**
     * @return Collection<int, Review>
     */
    public function getReviews(): Collection
    {
        return $this->reviews;
    }

    public function addReview(Review $review): self
    {
        if (!$this->reviews->contains($review)) {
            $this->reviews[] = $review;
            $review->setProduct($this);
        }

        return $this;
    }

    public function removeReview(Review $review): self
    {
        if ($this->reviews->removeElement($review)) {
            // set the owning side to null (unless already changed)
            if ($review->getProduct() === $this) {
                $review->setProduct(null);
            }
        }

        return $this;
    }


}
