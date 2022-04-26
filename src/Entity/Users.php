<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;

use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;

/**
 * Users
 * @ORM\Entity(repositoryClass=UsersRepository::class)
 * @ORM\Table(name="users", indexes={@ORM\Index(name="users_fk0", columns={"id_role"})})
 * @ORM\Entity
 * @UniqueEntity(
 *     fields= {"email"},
 *     message= "l'email que vous avez indiqué est déja utilisé"
 * )
 */
class Users implements UserInterface
{
    /**
     * @var int

     * @ORM\Column(name="id_user", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idUser;

    /**
     *
     *
     *
     * @Assert\NotBlank(message="veuillez rentrez vos données")
     * @var string
     *
     * @ORM\Column(name="firstname", type="string", length=255, nullable=false)
     *
     *  @Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="le nom ne doit pas contenir des chiffres"
     * )

     */
    private $firstname;

    /**
     *
     * @Assert\NotBlank(message="veuillez rentrez vos données")
     * @var string
     *
     * @ORM\Column(name="lastname", type="string", length=255, nullable=false)
     * * @Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="le prenom name cannot contain a number"
     *)
     */
    private $lastname;

    /**
     * @Assert\NotBlank(message="veuillez rentrez vos données")
     * @Assert\Email(
     *     message = "veuillez entrez un email valid."
     * )
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=255, nullable=false)
     */
    private $email;

    /**
     *
     * @Assert\Length(
     *      min = 2,
     *      max = 8,
     *      minMessage = "votre mot de passe doit avoir plus que 2 caractéres",
     *      maxMessage = "votre mot de passe ne doit pas depassé 8 caractéres"
     * )
     * @Assert\NotBlank(message="veuillez rentrez vos données")
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=255, nullable=false)
     */
    private $password;

    /**
     *
     *  @Assert\NotBlank(message="veuillez rentrez vos données")
     * @Assert\Type(
     *     type="integer",
     *     message="votre numero doit contenir que des nombres."
     * )
     *
     * @var int
     *
     * @ORM\Column(name="phone_number", type="integer", nullable=false)
     */
    private $phoneNumber;

    /**
     *
     * @var \Role
     *
     * @ORM\ManyToOne(targetEntity="Role", inversedBy="Users")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_role", referencedColumnName="id_role")
     * })
     */
    private $idRole;

    /**
     *
     * @var string
     *
     * @ORM\Column(name="check_account", type="string", length=20, nullable=false)
     */
    private $checkAccount;
    protected $captchaCode;

    public function getIdUser(): ?int
    {
        return $this->idUser;
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

    public function getLastname(): ?string
    {
        return $this->lastname;
    }

    public function setLastname(string $lastname): self
    {
        $this->lastname = $lastname;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getPhoneNumber(): ?int
    {
        return $this->phoneNumber;
    }

    public function setPhoneNumber(int $phoneNumber): self
    {
        $this->phoneNumber = $phoneNumber;

        return $this;
    }

    public function getIdRole(): ?Role
    {
        return $this->idRole;
    }

    public function setIdRole(?Role $idRole): self
    {
        $this->idRole = $idRole;

        return $this;
    }

    public function getCheckAccount()
    {
        return $this->checkAccount;
    }

    public function setCheckAccount(string $checkAccount): self
    {
        $this->checkAccount = $checkAccount;

        return $this;
    }


    public function getRoles()
    {
            return [$this->getIdRole()->getRolename()];
    }

    public function getSalt()
    {
           return $this->getCheckAccount();
    }

    public function getUsername()
    {

    }

    public function eraseCredentials()
    {

    }
    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }
}
