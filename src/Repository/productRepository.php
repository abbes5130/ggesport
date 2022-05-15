<?php

namespace App\Repository;

use App\Entity\Review;
use App\Entity\Product;
use App\Entity\Category;
use Doctrine\ORM\ORMException;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;

/**
 * @method Product|null find($id, $lockMode = null, $lockVersion = null)
 * @method Product|null findOneBy(array $criteria, array $orderBy = null)
 * @method Product[]    findAll()
 * @method Product[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class productRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Product::class);

    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Product $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Product $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // public function findPagination():PaginationInterface
    // {
    //     return $this->paginator->paginate(
    //         $this->findAll(),
    //         1,
    //         6
    //     );
        
    // }


    // public function findproductwithreviews(string $id){
    //     $qb = $this->createQueryBuilder('p')
    //     ->innerJoin('App\Entity\Review', 'r')
    //     ->addSelect('r')
    //     ->where('p.productName = :id')
    //     ->setParameter('id', $id)
    //     ->getQuery()->getResult();
    // }

    public function getRelatedProducts(string $categoryName){
        $query = $this->createQueryBuilder('p')
            ->select('p.idProduct,p.productName,p.image,p.productPrice,p.description')
            ->innerJoin('p.category' , 'c')
            ->addSelect('c.categoryName')
            ->andwhere('c.categoryName LIKE :categoryName')
            ->setParameter('categoryName', $categoryName);
            // dump($query->getQuery()->getResult());
            // die();

            return $query->getQuery()->getResult();
    }


    public function getFilteredProducts(string $searchString, int $minPrice, int $maxPrice, string $categoryName){

        $query = $this->createQueryBuilder('p')
            ->select('p.idProduct,p.productName,p.image,p.productPrice,p.description')
            ->innerJoin('p.category' , 'c')
            ->addSelect('c.categoryName');
            


            if(!empty($searchString)){
                $query= $query
                ->andWhere('p.productName LIKE :ss')
                ->setParameter('ss', "%{$searchString}%");
            }

            if(!empty($minPrice)){
                $query= $query
                ->andWhere('p.productPrice > :min')
                ->setParameter('min', $minPrice);
            }

            if(!empty($maxPrice)){
                $query= $query
                ->andWhere('p.productPrice < :max')
                ->setParameter('max', $maxPrice);
            }

            if(!empty($categoryName) && ($categoryName!="ALL")){
                $query= $query
                ->andwhere('c.categoryName LIKE :categoryName')
                ->setParameter('categoryName', $categoryName);
            }

            return $query->getQuery()->getResult();

        // dump($query->getQuery()->getResult());
        // die();

        // $entityManager = $this->getEntityManager();

        // $query = $entityManager->createQuery(
        //     'SELECT p ,c.categoryName
        //     FROM App\Entity\Product p
        //     INNER JOIN App\Entity\Category c
        //     WHERE p.productPrice > :min
        //     AND p.productPrice < :max
        //     And c.categoryName= :cname'
        // )->setParameter('min', $minPrice)
        // ->setParameter('max', $maxPrice)
        // ->setParameter('cname', $categoryName);
        // return $query->getResult();

        // dump($query->execute());
        // die();
    }


    // /**
    //  * @return Product[] Returns an array of Product objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Product
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}


// $query = $entityManager->createQuery(
        //     'SELECT p, c
        //     FROM App\Entity\Product p
        //     INNER JOIN p.category c
        //     WHERE c.categoryName = :n
        //     AND p.productPrice > :min
        //     AND p.productPrice < :max'
        // )->setParameter('n', $categoryName)
        // ->setParameter('min', $minPrice)
        // ->setParameter('max', $maxPrice);