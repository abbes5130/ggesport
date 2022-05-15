<?php

namespace App\Entity;

use App\Repository\LikesRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=LikesRepository::class)
  *@ORM\Table(name="Likes")
 */
class Likes
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;
  /**
     * @ORM\ManyToOne(targetEntity=Comments::class, inversedBy="likes")
     * @ORM\JoinColumn(name="comments_id", referencedColumnName="id")

     */
    private $comments;
    public function getId(): ?int
    {
        return $this->id;
    }
    public function getComments()
    {
        return $this->comments;
    }

    public function setComments($Comments)
    {
        $this->comments = $Comments;

        return $this;
    }
}
