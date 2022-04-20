<?php

namespace App\Entity;

use App\Repository\CommentsRepository;
use Doctrine\ORM\Mapping as ORM;

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
     */
    private $id;

    /**
     * @var string
     * @ORM\Column(type="text", unique=false)
     */
    private $text;

    /**
     * @var \DateTime
     * @ORM\Column(type="date",name="comment_date")
     */
    private $comment_date;

    /**
     * @ORM\ManyToOne(targetEntity=News::class, inversedBy="comments")
     * @ORM\JoinColumn(name="news_id", referencedColumnName="id")
     */
    private $News;

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
}
