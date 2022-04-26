<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Player
 *
 * @ORM\Table(name="player", indexes={@ORM\Index(name="player_fk1", columns={"id_game"}), @ORM\Index(name="player_fk0", columns={"id_team"})})
 * @ORM\Entity
 */
class Player
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_player", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPlayer;

    /**
     * @var int
     *
     * @ORM\Column(name="id_team", type="integer", nullable=false)
     */
    private $idTeam;

    /**
     * @var string
     *
     * @ORM\Column(name="firstname", type="string", length=255, nullable=false)
     */
    private $firstname;

    /**
     * @var int
     *
     * @ORM\Column(name="id_game", type="integer", nullable=false)
     */
    private $idGame;

    /**
     * @var string
     *
     * @ORM\Column(name="lastname", type="string", length=255, nullable=false)
     */
    private $lastname;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="player_tag", type="string", length=255, nullable=false)
     */
    private $playerTag;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
    private $photo;

    public function getIdPlayer(): ?int
    {
        return $this->idPlayer;
    }

    public function getIdTeam(): ?int
    {
        return $this->idTeam;
    }

    public function setIdTeam(int $idTeam): self
    {
        $this->idTeam = $idTeam;

        return $this;
    }

    public function getFirstname(): ?string
    {
        return $this->firstname;
    }

    public function setFirstname(string $firstname): self
    {
        $this->firstname = $firstname;

        return $this;
    }

    public function getIdGame(): ?int
    {
        return $this->idGame;
    }

    public function setIdGame(int $idGame): self
    {
        $this->idGame = $idGame;

        return $this;
    }

    public function getLastname(): ?string
    {
        return $this->lastname;
    }

    public function setLastname(string $lastname): self
    {
        $this->lastname = $lastname;

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

    public function getPlayerTag(): ?string
    {
        return $this->playerTag;
    }

    public function setPlayerTag(string $playerTag): self
    {
        $this->playerTag = $playerTag;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }


}
