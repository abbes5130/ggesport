<?php

namespace App\Form;

use App\Entity\Role;
use App\Entity\Users;
use App\Repository\RoleRepository;
use Doctrine\ORM\EntityRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;

class RegistrationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('firstname')
            ->add('lastname')
            ->add('email')
            ->add('password',RepeatedType::class,
            ['type'=>PasswordType::class,
                'first_options'=>['label'=>'Password'],
                'second_options'=>['label'=>'confirm Password']
        ])
            ->add('phoneNumber')
           // ->add('idRole',EntityType::class,['class'=>Role::class,'choice_label'=>'rolename']);
            ->add('idRole', EntityType::class,[
                        'class' => Role::class,
                        'choice_label' => 'rolename',
                       'query_builder' => function(EntityRepository $repository){
                            return $repository->createQueryBuilder('r')->where('r.rolename LIKE ?1') ->setParameter('1', 'Membre');

                       }
                                ])

            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptchaUserRegistration',
                'constraints' => [
                    new ValidCaptcha([
                        'message' => 'Invalid captcha, please try again',
                    ]),
                ],
            ));

    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Users::class,
        ]);
    }
}
