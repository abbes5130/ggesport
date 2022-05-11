<?php

namespace App\Repository;
use App\Entity\Tag;
use App\Entity\Newcategorie;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;

use App\Entity\News;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\Tools\Pagination\Paginator;
/**
 * @method News|null find($id, $lockMode = null, $lockVersion = null)
 * @method News|null findOneBy(array $criteria, array $orderBy = null)
 * @method News[]    findAll()
 * @method News[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class NewsRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, News::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(News $entity, bool $flush = true): void
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
    public function remove(News $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return News[] Returns an array of News objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('n')
            ->andWhere('n.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('n.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?News
    {
        return $this->createQueryBuilder('n')
            ->andWhere('n.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function findPostByid($id)
    {
        try {
            return $this->getEntityManager()
                ->createQuery(
                    "SELECT n
                FROM App:News
                n WHERE n.id =:id"
                )
                ->setParameter('id', $id)
                ->getOneOrNullResult();
        } catch (NonUniqueResultException $e) {
        }
    }
    public function findEntitiesByString($str){
        return $this->getEntityManager()
            ->createQuery(
                'SELECT n
                FROM App:News n
                WHERE n.title LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }  
     /**
     * @param int $page
     * @param int $max
     * @param null $tag
     * @return Paginator
     */
    public function findByPage($page = 1, $max = 2, $tag = null)
    {
        $dql = $this->createQueryBuilder('News');
        if (!$tag) {
            $dql->orderBy('News.creation_date', 'DESC');
        } else {
            $matchedTag = $this->getEntityManager()->getRepository(Tag::class)->findOneBy(["text" => $tag]);
            $dql->innerJoin("News.tag", "tag")
                ->orderBy('News.creation_date', 'DESC')
                ->Where("tag.id = '" . $matchedTag->getId() . "'");
        }
        $firstResult = ($page - 1) * $max;

        $query = $dql->getQuery();
        $query->setFirstResult($firstResult);
        $query->setMaxResults($max);

        $paginator = new Paginator($query);

        if (($paginator->count() <= $firstResult) && $page != 1) {
            throw new NotFoundHttpException('Page not found');
        }

        return $paginator;
    }

    public function findArticlesByCategoryQuery($id)
    {
        return $this->createQueryBuilder('a')
            ->select('a')
            ->join('a.Newcategorie', 'c')
            ->where('c.id = :newcategory_id')
            ->orderBy('a.creation_date', 'DESC')
            ->setParameter('newcategory_id', $id)
            ->getQuery()
            ;
    }
    /**
     * @param int $page
     * @param int $max
     * @param null $Newcategorie
     * @return Paginator
     */
    public function findByPage1($page = 1, $max = 2, $Newcategorie = null)
    {
        $dql = $this->createQueryBuilder('News');
        if (!$Newcategorie) {
            $dql->orderBy('News.creation_date', 'DESC');
        } else {
            $matchedTag = $this->getEntityManager()->getRepository(Newcategorie::class)->findOneBy(["name" => $Newcategorie]);
            $dql->innerJoin("News.newcategorie", "newcategorie")
                ->orderBy('News.creation_date', 'DESC')
                ->Where("newcategorie.id = '" . $matchedTag->getId() . "'");
        }
        $firstResult = ($page - 1) * $max;

        $query = $dql->getQuery();
        $query->setFirstResult($firstResult);
        $query->setMaxResults($max);

        $paginator = new Paginator($query);

        if (($paginator->count() <= $firstResult) && $page != 1) {
            throw new NotFoundHttpException('Page not found');
        }

        return $paginator;
    }

}
