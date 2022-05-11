<?php

namespace App\Form;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use App\Entity\Newcategorie;

use App\Entity\News;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class NewsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('title',TextType::class, array('data_class'=>null, 'required'=>false))
        ->add('bg_img',FileType::class, array('data_class'=>null, 'required'=>false))
        ->add('img',FileType::class, array('data_class'=>null, 'required'=>false))
        ->add('description', TextareaType::class)
        ->add('Submit', SubmitType::class)
        ->add('Newcategorie', EntityType::class, [
            'attr' => [
                'class' => 'form-control form-group'
            ],
            'class' => Newcategorie::class,
            'choice_label' => 'name'
        ])
        ->add('save', SubmitType::class, ['label' => 'Create Task'])

    ;
    
}
/**
 * {@inheritdoc}
 */
    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => News::class,
        ]);
    }
     /**
 * {@inheritdoc}
 */
public function getBlockPrefix()
{
    return 'News';
}
}
