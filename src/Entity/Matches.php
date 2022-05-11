<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Matches
 *
 * @ORM\Table(name="matches", indexes={@ORM\Index(name="matches_fk1", columns={"id_Team_2"}), @ORM\Index(name="matches_fk0", columns={"id_Team_1"})})
 * @ORM\Entity
 */
class Matches
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_match", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("matches")
     */
    private $idMatch;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="time", type="time", nullable=false)
     * @Groups("matches")
     */
    private $time;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @Groups("matches")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="location", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 4,
     *      max = 20,
     *      minMessage = "Your location must be at least {{ limit }} characters long",
     *      maxMessage = "Your location cannot be longer than {{ limit }} characters"
     * )
     * @Groups("matches")
     */
    private $location;

    /**
     * @var int|null
     *
     * @ORM\Column(name="nb_seats", type="integer", nullable=true)
     * @Assert\NotBlank
     * @Groups("matches")
     */
    private $nbSeats;

    /**
     * @var int
     *
     * @ORM\Column(name="price", type="integer", nullable=false)
     * @Assert\NotBlank
     * @Groups("matches")
     */
    private $price;

    /**
     * @var string|null
     *
     * @ORM\Column(name="link", type="string", length=255, nullable=true)
     * @Assert\Length(
     *      min = 4,
     *      max = 20,
     *      minMessage = "Your link must be at least {{ limit }} characters long",
     *      maxMessage = "Your link cannot be longer than {{ limit }} characters"
     * )
     * @Groups("matches")

     */
    private $link;

    /**
     * @var \Team
     *
     * @ORM\ManyToOne(targetEntity="Team")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_Team_1", referencedColumnName="id_team")
     * })
     * @Groups("matches")
     */
    private $idTeam1;

    /**
     * @var \Team
     *
     * @ORM\ManyToOne(targetEntity="Team")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_Team_2", referencedColumnName="id_team")
     * })
     * @Groups("matches")
     */
    private $idTeam2;

    public function getIdMatch(): ?int
    {
        return $this->idMatch;
    }

    public function getTime(): ?\DateTimeInterface
    {
        return $this->time;
    }

    public function setTime(\DateTimeInterface $time): self
    {
        $this->time = $time;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getLocation(): ?string
    {
        return $this->location;
    }

    public function setLocation(string $location): self
    {
        $this->location = $location;

        return $this;
    }

    public function getNbSeats(): ?int
    {
        return $this->nbSeats;
    }

    public function setNbSeats(?int $nbSeats): self
    {
        $this->nbSeats = $nbSeats;

        return $this;
    }

    public function getPrice(): ?int
    {
        return $this->price;
    }

    public function setPrice(int $price): self
    {
        $this->price = $price;

        return $this;
    }

    public function getLink(): ?string
    {
        return $this->link;
    }

    public function setLink(?string $link): self
    {
        $this->link = $link;

        return $this;
    }

    public function getIdTeam1(): ?Team
    {
        return $this->idTeam1;
    }

    public function setIdTeam1(?Team $idTeam1): self
    {
        $this->idTeam1 = $idTeam1;

        return $this;
    }

    public function getIdTeam2(): ?Team
    {
        return $this->idTeam2;
    }

    public function setIdTeam2(?Team $idTeam2): self
    {
        $this->idTeam2 = $idTeam2;

        return $this;
    }




}