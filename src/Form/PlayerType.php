<?php

namespace App\Form;
use App\Entity\Team;
use App\Entity\Player;
use App\Entity\Game;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class PlayerType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('firstname')
            ->add('lastname')
            ->add('description')
            ->add('playerTag')
            ->add('photo')
            ->add('idTeam', EntityType::class,
            
            [
                'class'=> Team::class,
                'choice_label'=>'teamName'
            ]
            )
            ->add('idGame', EntityType::class,
            
            [
                'class'=> Game::class,
                'choice_label'=>'gameName'
            ]
            )
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Player::class,
        ]);
    }
}
