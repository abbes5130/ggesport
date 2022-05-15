<?php

namespace App\Form;

use App\Entity\Product;
use App\Entity\Category;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class ProductType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('productName',TextType::class,[
                'constraints' => [
                    new NotBlank(),
                    new Length(['min' => 3]),
                ],
                'required'=> false
            ])
            ->add('image', FileType::class,[
                'mapped'=>false,
                'row_attr' => ['class' => 'fieldClass']
            ])
            ->add('productPrice')
            ->add('description')
            ->add('color')
            ->add('mark')
            ->add('discount')
            ->add('disponibility')
            ->add('stockQuantity')
            ->add('category', EntityType::class, [
                'class' => Category::class,
                'choice_label' => 'category_name'
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Product::class,
        ]);
    }
}
