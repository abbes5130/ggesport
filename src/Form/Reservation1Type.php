<?php

namespace App\Form;

use App\Entity\Matches;
use App\Entity\Reservation;
use App\Entity\Users;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class Reservation1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('idMatch',EntityType::class,

                [
                    'class'=> Matches::class,
                    'choice_label'=>'idMatch'
                ])
            ->add('idUser',EntityType::class,

                [
                    'class'=> Users::class,
                    'choice_label'=>'idUser'
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
