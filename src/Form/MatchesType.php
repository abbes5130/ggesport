<?php

namespace App\Form;

use App\Entity\Matches;
use App\Entity\Team;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
class MatchesType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('time')
            ->add('date')
            ->add('location')
            ->add('nbSeats')
            ->add('price')
            ->add('link')
            ->add('idTeam1',EntityType::class,

            [
                'class'=> Team::class,
                'choice_label'=>'teamName'
            ])
            ->add('idTeam2',EntityType::class,

                [
                    'class'=> Team::class,
                    'choice_label'=>'teamName'
                ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Matches::class,
        ]);
    }
}
