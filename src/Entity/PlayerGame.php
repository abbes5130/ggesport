<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PlayerGame
 *
 * @ORM\Table(name="player_game", indexes={@ORM\Index(name="player_game_fk1", columns={"id_joueur"}), @ORM\Index(name="player_game_fk0", columns={"id_game"})})
 * @ORM\Entity
 */
class PlayerGame
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_game_player", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idGamePlayer;

    /**
     * @var \Player
     *
     * @ORM\ManyToOne(targetEntity="Player")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_joueur", referencedColumnName="id_player")
     * })
     */
    private $idJoueur;

    /**
     * @var \Game
     *
     * @ORM\ManyToOne(targetEntity="Game")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_game", referencedColumnName="id_game")
     * })
     */
    private $idGame;

    public function getIdGamePlayer(): ?int
    {
        return $this->idGamePlayer;
    }

    public function getIdJoueur(): ?Player
    {
        return $this->idJoueur;
    }

    public function setIdJoueur(?Player $idJoueur): self
    {
        $this->idJoueur = $idJoueur;

        return $this;
    }

    public function getIdGame(): ?Game
    {
        return $this->idGame;
    }

    public function setIdGame(?Game $idGame): self
    {
        $this->idGame = $idGame;

        return $this;
    }


}
