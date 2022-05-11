<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use App\Repository\CommentsRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=CommentsRepository::class)
 *@ORM\Table(name="Comments")
 */
class Comments
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @var string
     * @ORM\Column(type="text", unique=false)
     * @Groups("post:read")
     */
    private $text;

    /**
     * @var \DateTime
     * @ORM\Column(type="date",name="comment_date")
     * @Groups("post:read")
     */
    private $comment_date;

    /**
     * @ORM\ManyToOne(targetEntity=News::class, inversedBy="comments")
     * @ORM\JoinColumn(name="news_id", referencedColumnName="id")
     * @Groups("comment")
     */
    private $News;
     /**
     * @ORM\OneToMany(targetEntity=Likes::class, mappedBy="comments", cascade={"persist", "remove"})
     */
    private $likes;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getText(): ?string
    {
        return $this->text;
    }

    public function setText(string $text): self
    {
        $this->text = $text;

        return $this;
    }
/**
     * @return \DateTime comment_date
     */
    public function getCommentDate(): ?\DateTimeInterface
    {
        return $this->comment_date;
    }
 /**
     * Set comment_date
     *
     * @param \DateTime $comment_date
     *
     * @return MisesEnPlace
     */
    public function setCommentDate(\DateTimeInterface $comment_date): self
    {
        $this->comment_date = $comment_date;

        return $this;
    }

    public function getNews()
    {
        return $this->News;
    }

    public function setNews($News)
    {
        $this->News = $News;

        return $this;
    }
    public function __toString()
{
    return (string) $this->getNews();
}
 /**
     * @return Collection|Likes[]
     */
    public function getLikes(): Collection
    {
        return $this->likes;
    }

    public function addLike(Likes $like): self
    {
        if (!$this->likes->contains($like)) {
            $this->likes[] = $like;
            $like->setComments($this);
        }

        return $this;
    }

    public function removeLike(Likes $like): self
    {
        if ($this->likes->contains($like)) {
            $this->likes->removeElement($like);
            // set the owning side to null (unless already changed)
            if ($like->getComments() === $this) {
                $like->setComments(null);
            }
        }

        return $this;
    }

}
