<?php

namespace App\Form;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use App\Entity\Comments;
use App\Entity\News;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CommentsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('text', TextareaType::class, [
                'label'=>'votre commentaire',
                'attr'=>[
                    'class'=> 'form-control'
                ]
            ])
            ->add('News', EntityType::class, [
                'attr' => [
                    'class' => 'form-control form-group'
                ],
                'class' => News::class,
                'choice_label' => 'title'
            ])
            ->add('save', SubmitType::class, ['label' => 'Create Task'])

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Comments::class,
        ]);
    }
}
