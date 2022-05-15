<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Team
 *
 * @ORM\Table(name="team")
 * @ORM\Entity
 */
class Team
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_team", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("matches")
     * @Groups("teams")
     * @Groups("post:read")
     */
    private $idTeam;

    /**
     * @var string
     *
     * @ORM\Column(name="team_name", type="string", length=255, nullable=false)
     * @Groups("teams")
     * @Groups("matches")
     * @Groups("post:read")
     */
    private $teamName;

    /**
     * @var string
     *
     * @ORM\Column(name="logo", type="string", length=255, nullable=false)
     * @Groups("post:read")
     */
    private $logo;

    /**
     * @var int
     *
     * @ORM\Column(name="players_number", type="integer", nullable=false)
     * @Groups("post:read")
     */
    private $playersNumber;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     * @Groups("post:read")
     */
    private $description;

    public function getIdTeam(): ?int
    {
        return $this->idTeam;
    }

    public function getTeamName(): ?string
    {
        return $this->teamName;
    }

    public function setTeamName(string $teamName): self
    {
        $this->teamName = $teamName;

        return $this;
    }

    public function getLogo(): ?string
    {
        return $this->logo;
    }

    public function setLogo(string $logo): self
    {
        $this->logo = $logo;

        return $this;
    }

    public function getPlayersNumber(): ?int
    {
        return $this->playersNumber;
    }

    public function setPlayersNumber(int $playersNumber): self
    {
        $this->playersNumber = $playersNumber;

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


}
