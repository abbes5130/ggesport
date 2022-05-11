<?php

namespace App\Entity;

use App\Repository\NewcategorieRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=NewcategorieRepository::class)
 * @ORM\Table(name="newcategorie")
 */
class Newcategorie
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $name;

    /**
     * @ORM\OneToMany(targetEntity=News::class, mappedBy="newcategorie")
     */
    private $News;

    public function __construct()
    {
        $this->News = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(?string $name): self
    {
        $this->name = $name;

        return $this;
    }

    /**
     * @return Collection<int, News>
     */
    public function getNews(): Collection
    {
        return $this->News;
    }

    public function addNews(News $news): self
    {
        if (!$this->News->contains($news)) {
            $this->News[] = $news;
            $news->setNewcategorie($this);
        }

        return $this;
    }

    public function removeNews(News $news): self
    {
        if ($this->News->removeElement($news)) {
            // set the owning side to null (unless already changed)
            if ($news->getNewcategorie() === $this) {
                $news->setNewcategorie(null);
            }
        }

        return $this;
    }
}
