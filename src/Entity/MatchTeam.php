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
     * @var \Matchs
     *
     * @ORM\ManyToOne(targetEntity="Matchs")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_match", referencedColumnName="id_match")
     * })
     */
    private $idMatch;

    /**
     * @var \Team
     *
     * @ORM\ManyToOne(targetEntity="Team")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_team", referencedColumnName="id_team")
     * })
     */
    private $idTeam;

    public function getIdMatchTeam(): ?int
    {
        return $this->idMatchTeam;
    }

    public function getIdMatch(): ?Matchs
    {
        return $this->idMatch;
    }

    public function setIdMatch(?Matchs $idMatch): self
    {
        $this->idMatch = $idMatch;

        return $this;
    }

    public function getIdTeam(): ?Team
    {
        return $this->idTeam;
    }

    public function setIdTeam(?Team $idTeam): self
    {
        $this->idTeam = $idTeam;

        return $this;
    }


}
