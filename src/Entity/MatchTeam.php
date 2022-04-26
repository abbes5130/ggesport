<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * MatchTeam
 *
 * @ORM\Table(name="match_team", indexes={@ORM\Index(name="match_team_fk1", columns={"id_team"}), @ORM\Index(name="match_team_fk0", columns={"id_match"})})
 * @ORM\Entity
 */
class MatchTeam
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_match_team", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMatchTeam;

    /**
     * @var int
     *
     * @ORM\Column(name="id_match", type="integer", nullable=false)
     */
    private $idMatch;

    /**
     * @var int
     *
     * @ORM\Column(name="id_team", type="integer", nullable=false)
     */
    private $idTeam;

    public function getIdMatchTeam(): ?int
    {
        return $this->idMatchTeam;
    }

    public function getIdMatch(): ?int
    {
        return $this->idMatch;
    }

    public function setIdMatch(int $idMatch): self
    {
        $this->idMatch = $idMatch;

        return $this;
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


}
