<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use App\Repository\TagRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**

 * @ORM\Entity(repositoryClass=TagRepository::class)
 */
class Tag
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $text;
      /**
     * @ORM\ManyToMany(targetEntity=News::class, mappedBy="tag")
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
     * @return Collection|News[]
     */
    public function getNews(): Collection
    {
        return $this->News;
    }

    public function addNews(News $News): self
    {
        if (!$this->Newss->contains($News)) {
            $this->News[] = $News;
            $News->addTag($this);
        }

        return $this;
    }

    public function removeNews(News $News): self
    {
        if ($this->Newss->contains($News)) {
            $this->Newss->removeElement($News);
            $News->removeTag($this);
        }

        return $this;
    }
}
