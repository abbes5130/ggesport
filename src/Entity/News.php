<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;

use App\Repository\NewsRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=NewsRepository::class)
 * @ORM\HasLifecycleCallbacks()
 * @ORM\Table(name="News")
 */
class News
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string")
     *  @Assert\Length(
     *      min = 2,
     *      max = 11,
     *      minMessage = "Your first name must be at least {{ limit }} characters long",
     *      maxMessage = "Your first name cannot be longer than {{ limit }} characters"
     * )
     * @Assert\NotNull
     */
    private $title;

    /**
     * @ORM\Column(type="string", length=500, nullable=true)
      * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
      *@Assert\NotNull
      */
    private $bg_img;

    /**
     * @ORM\Column(type="string", length=500)
     * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
      */
    private $img;

    /**
     * @ORM\Column(type="string")
      * @Assert\Length(
     *      min = 2,
     *      max = 50,
     *      minMessage = "Your first name must be at least {{ limit }} characters long",
     *      maxMessage = "Your first name cannot be longer than {{ limit }} characters"
     * 
     * )
     * @Assert\NotNull
     */
    private $description;

    /**
     *@var \DateTime
     * @ORM\Column(type="date",name="creation_date")
     */
    private $creation_date;

    /**
     * @ORM\OneToMany(targetEntity=Comments::class, mappedBy="News")
     */
    private $comments;

    public function __construct()
    {
        $this->comments = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): self
    {
        $this->title = $title;

        return $this;
    }

    public function getBgImg(): ?string
    {
        return $this->bg_img;
    }

    public function setBgImg(string $bg_img): self
    {
        $this->bg_img = $bg_img;

        return $this;
    }

    public function getImg(): ?string
    {
        return $this->img;
    }

    public function setImg(string $img): self
    {
        $this->img = $img;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }
/**
     * @return \DateTime creation_date
     */
    public function getCreationDate(): ?\DateTimeInterface
    {
        return $this->creation_date;
    }
  /**
     * Set creation_date
     *
     * @param \DateTime $creation_date
     *
     * @return MisesEnPlace
     */
    public function setCreationDate(\DateTimeInterface $creation_date): self
    {
        $this->creation_date = $creation_date;

        return $this;
    }

    /**
     * @return Collection<int, Comments>
     */
    public function getComments(): Collection
    {
        return $this->comments;
    }

    public function addComment(Comments $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setNews($this);
        }

        return $this;
    }

    public function removeComment(Comments $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getNews() === $this) {
                $comment->setNews(null);
            }
        }

        return $this;
    }
}
