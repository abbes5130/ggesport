<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Game
 *
 * @ORM\Table(name="game")
 * @ORM\Entity
 */
class Game
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_game", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idGame;

    /**
     * @var string
     *
     * @ORM\Column(name="game_name", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      max = 30,
     *      minMessage = "The Game Name must be at least {{ limit }} characters long",
     *      maxMessage = "The Game Name characters long must be lower than {{ limit }} characters long"
     * )
     * @Assert\NotBlank
     */

    private $gameName;

    /**
     * @var string
     *
     * @ORM\Column(name="logo", type="string", length=255, nullable=false)
     * @Assert\NotBlank
     */
    private $logo;

    public function getIdGame(): ?int
    {
        return $this->idGame;
    }

    public function getGameName(): ?string
    {
        return $this->gameName;
    }

    public function setGameName(string $gameName): self
    {
        $this->gameName = $gameName;

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


}
