<?php

namespace App\Entity;

use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Serializer\Annotation\Groups;

use Doctrine\ORM\Mapping as ORM;

/**
 * Role
 * @ORM\Entity(repositoryClass="App\Repository\RoleRepository")
 * @ORM\Table(name="role")
 * @ORM\Entity
 */
class Role
{
    /**
     * @var int

     * @ORM\Column(name="id_role", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     *  @Groups ("Users")


     */
    private $idRole;

    /**
     * @var string
     *  @Assert\NotBlank(message="veuillez rentrez vos données")
     * @Assert\Type("string")
     * @ORM\Column(name="rolename", type="string", length=255, nullable=false)
     * @Groups ("Users")
     */
    private $rolename;
    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Users", mappedBy="Role")


     */
    private $utilisateur;

    public function __construct()
    {
        $this->utilisateur = new ArrayCollection();


    }

    public function getIdRole(): ?int
    {
        return $this->idRole;
    }

    /**
     * @param int $idRole
     */
    public function setIdRole(int $idRole): void
    {
        $this->idRole = $idRole;
    }



    public function getRolename(): ?string
    {
        return $this->rolename;
    }

    public function setRolename(string $rolename): self
    {
        $this->rolename = $rolename;

        return $this;
    }

    /**
     * @return Collection<int, Users>
     */
    public function getUtilisateur(): Collection
    {
        return $this->utilisateur;
    }

    public function addUtilisateur(Users $utilisateur): self
    {
        if (!$this->utilisateur->contains($utilisateur)) {
            $this->utilisateur[] = $utilisateur;
            $utilisateur->setRole($this);
        }

        return $this;
    }

    public function removeUtilisateur(Users $utilisateur): self
    {
        if ($this->utilisateur->removeElement($utilisateur)) {
            // set the owning side to null (unless already changed)
            if ($utilisateur->getRole() === $this) {
                $utilisateur->setRole(null);
            }
        }

        return $this;
    }


}
