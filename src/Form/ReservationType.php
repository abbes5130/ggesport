<?php

namespace App\Form;

use App\Entity\Matches;
use App\Entity\Reservation;
use App\Entity\Users;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class ReservationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('firstname')
            ->add('lastname')
            ->add('date')
            ->add('time')
            ->add('location')
            ->add('price')
            ->add('idUser',EntityType::class,

                [
                    'class'=> Users::class,
                    'choice_label'=>'idUser'
                ])
            ->add('idMatch',EntityType::class,

                [
                    'class'=> Matches::class,
                    'choice_label'=>'idMatch'
                ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reservation::class,
        ]);
    }
}
